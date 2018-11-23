<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>农场商品</title>
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
	 $("body").delegate(".unit-price","click",function(){
    	load.post("/protal/farm/cart/add", {farmId:$("#farm-list").data("id"),farmProductId:$(this).data("id")}, false, function(data) {
    		tip.msg(data.msg);
		});
     })
})

function loadData(){
	load.post("/protal/farm/product/list", {farmId:$("#farm-list").data("id")}, false, function(data) {
		if(data){
			handlebars.loadTemplate("#farm-list", "#list-template", data);
		}
	});
}
</script>
</head>
<body>
<div class="head">
    <a href="/protal/farm/farm" class="return"><i class="icon-chevron-left"></i> 返回</a>
   	 ${farm.name}
    <a href="/protal/farm/cart/${farm.id}" class="search"><i class="icon-shopping-cart"></i> </a>
</div>
<ul class="house-list" id="farm-list" data-id="${farm.id}">
</ul>
<script id="list-template" type="text/x-handlebars-template">
	{{#each this}}
	<li>
		<div class="con">
            <div class="house-pic animated fadeInLeft"><img src="{{pictures}}"></div>
            <div class="house-detail">
                <h3 class="animated fadeInRight">{{title}}</h3>
                <div class="house-price">
                	<br><br><br>
                	<span class="price-total">{{price}}元/斤</span>
                	<a href="#" class="unit-price" data-id="{{id}}">加入购物车</a>
                </div>
            </div>
        </div>
	</li>
	{{/each}}
</script>
</body>
</html>