<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>农场商品</title>
<link href="${request.contextPath}/static/protal/css/base.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/shake.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/handlebars/handlebars-v4.0.11.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/handlebars.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script>
$(function(){
	loadData();
	 $("body").delegate(".farm-sub","click",function(){
	 	var val = $(this).parent().find("input").val();
	 	if(val > 1){
	 		val--;
	 		$(this).parent().find("input").val(val);
	 		getTotal();
	 	}
     })
	 $("body").delegate(".farm-add","click",function(){
	 	var val = $(this).parent().find("input").val();
	 	val++;
 		$(this).parent().find("input").val(val);
 		getTotal();
     })
	 $("body").delegate(".farm-remove","click",function(){
    	load.post("/protal/farm/cart/delete", {farmId:$("#farm-list").data("id"),farmProductId:$(this).data("id")}, false, function(data) {
    		loadData()
    		tip.msg(data.msg);
		});
     })
})

function getTotal(){
	var total = 0;
	$(".shopcar").find("input").each(function(){
		total = total + $(this).data("price")*$(this).val();
	})
	$("#farm-total").html(total);
}

function loadData(){
	load.post("/protal/farm/cart/list", {farmId:$("#farm-list").data("id")}, false, function(data) {
		if(data){
			handlebars.loadTemplate("#farm-list", "#list-template", data);
			getTotal();
		}
	});
}

function btnSubmit(){
	var array = new Array();
	$(".shopcar").find("input").each(function(){
		var data = {
			farmProductId: $(this).data("id"),
			name: $(this).data("name"),
			price: $(this).data("price"),
			picture: $(this).data("pic"),
			num: $(this).val()
		}
		array.push(data);
	})
	if(array.length == 0){
		tip.msg("购物车为空！");
		return false;
	}
	tip.confirm("确认下单？",function(){
		load.postJson("/protal/farm/order/add/" + $("#farm-list").data("id"), JSON.stringify(array), false, function(data) {
	        if (data.status == 0) {
	        	tip.msg(data.msg);
	            window.location.href = "/protal/farm/order";
	        } else {
	            tip.msg(data.msg);
	        }
		});
	})
}
</script>
</head>
<body>
	<div class="head">
	    <a href="/protal/farm/product/${farm.id}" class="return"><i class="icon-chevron-left"></i> 返回</a>
	   	 购物车
	</div>
	<div class="contaniner fixed-contb" id="farm-list" data-id="${farm.id}">
	</div>
	<footer class="page-footer fixed-footer">
		<div class="shop-go">
			<b>合计：￥<font id="farm-total"></font>元</b>
			<span><a href="#" onclick="btnSubmit()">去下单</a></span>
		</div>
	</footer>
<script id="list-template" type="text/x-handlebars-template">
	{{#each this}}
	<section class="shopcar">
		<div class="shopcar-checkbox">
			<label for="shopcar" onselectstart="return false"></label>
		</div>
		<figure><img src="{{pictures}}"/></figure>
		<dl>
			<dt>{{title}}</dt>
			<div class="add">
				<span class="farm-sub">-</span>
				<input type="text" value="1" data-price="{{price}}" data-id="{{id}}" data-pic="{{pictures}}" data-name="{{title}}" />
				<span class="farm-add">+</span>
			</div>
			<h3>￥{{price}}元/斤</h3>
			<a href="#" class="farm-remove" data-id="{{id}}"><img src="/static/protal/img/shopcar-icon01.png"/></a>
		</dl>
	</section>
	{{/each}}
</script>
</body>
</html>