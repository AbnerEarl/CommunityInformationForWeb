<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>新房</title>
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
	$("#filter-tab>div").click(function(ev){
		$(this).addClass("on").siblings().removeClass("on");
		$("#filter-tab-list .list-area").eq($(this).index()).show().siblings().hide();
		var ev = ev || window.event;
		ev.cancelBubble = true;
		ev.stopPropagation();
	})
	$("#filter-bar>div").click(function(){
		if($("#filter-box").hasClass("show")){
			$("#filter-box").removeClass("show");
		}else{
			$("#filter-box").addClass("show");
		}
		var i = $(this).index();
		$("#filter-tab>div").eq(i).trigger("click")
	})
	$("#filter-box").click(function(){
		$(this).removeClass("show");
	})
	$("#tab_price li").click(function(){
		$("#search_price").html($(this).text());
		$("#search_price").data("value", $(this).find("a").data("value"));
		loadData();
	})
	$("#tab_room li").click(function(){
		$("#search_room").html($(this).text());
		$("#search_room").data("value", $(this).find("a").data("value"));
		loadData();
	})	
	$("#tab_acreage li").click(function(){
		$("#search_acreage").html($(this).text());
		$("#search_acreage").data("value", $(this).find("a").data("value"));
		loadData();
	})
})

function loadData(){
	var postData = {
		price: $("#search_price").data("value"),
		room: $("#search_room").data("value"),
		acreage: $("#search_acreage").data("value")
	}
	load.post("/protal/house/new/list", postData, false, function(data) {
		if(data){
			handlebars.loadTemplate("#house-list", "#list-template", data);
		}
	});
}
</script>
</head>
<body>
<div class="head">
    <a href="/protal/index" class="return"><i class="icon-chevron-left"></i> 返回</a>
   	 新房资讯
    <a href="/protal/house/new/sign" class="search"><i class="icon-edit"></i> </a>
</div>
<section class="filter-bar" id="filter-bar">
	<div class="filter-bar-price"><font id="search_price" data-value="-1">价格</font><i></i></div>
	<div class="filter-bar-fangxing"><font id="search_room" data-value="-1">房型</font><i></i></div>
	<div class="filter-bar-more"><font id="search_acreage" data-value="-1">面积</font><i></i></div>
</section>
<section class="filter-box" id="filter-box">
	<div class="filter-tab" id="filter-tab">
		<div class="tab-price">价格<i></i></div>
		<div class="tab-fangxing">房型<i></i></div>
		<div class="tab-more">面积<i></i></div>
	</div>
	<!--tab-list-->
	<div class="filter-tab-list" id="filter-tab-list">
		<!--价格-->
		<div class="list-area list-area-price" id="tab_price">
			<ul>
				<li><a href="#" data-value="-1">不限价格</a></li>
				<li><a href="#" data-value="1">5000元/平以下</a></li>
				<li><a href="#" data-value="2">5000-8000元/平</a></li>
				<li><a href="#" data-value="3">8000-1.2万元/平</a></li>
				<li><a href="#" data-value="4">1.2-2万元/平</a></li>
				<li><a href="#" data-value="5">2万元/平以上</a></li>
			</ul>
		</div>
		<!--房型-->
		<div class="list-area" id="tab_room">
			<ul>
				<li><a href="#" data-value="-1">不限房型</a></li>
				<li><a href="#" data-value="1">一室</a></li>
				<li><a href="#" data-value="2">二室</a></li>
				<li><a href="#" data-value="3">三室</a></li>
				<li><a href="#" data-value="4">四室</a></li>
				<li><a href="#" data-value="5">五室</a></li>
				<li><a href="#" data-value="6">五室以上</a></li>
			</ul>
		</div>
		<!--更多-->
		<div class="list-area list-area-more" id="tab_acreage">
			<ul>
				<li><a href="#" data-value="-1">不限面积</a></li>
				<li><a href="#" data-value="1">50平以下</a></li>
				<li><a href="#" data-value="2">50-90平</a></li>
				<li><a href="#" data-value="3">90-150平</a></li>
				<li><a href="#" data-value="4">150-200平</a></li>
				<li><a href="#" data-value="5">200平以上</a></li>
			</ul>
		</div>
	</div>
</section>
<ul class="house-list" id="house-list">
</ul>
<script id="list-template" type="text/x-handlebars-template">
	{{#each this}}
	<li>
		<a href="/protal/house/new/detail/{{id}}" class="house-link"></a>
		<div class="con">
            <div class="house-pic animated fadeInLeft"><img src="{{listFirst pictures}}"></div>
            <div class="house-detail">
                <h3 class="animated fadeInRight">{{title}}</h3>
                <div class="house-mj">{{countRoom}}室{{countHall}}厅{{countToilet}}卫</div>
                <div class="house-price">
                	<span class="price-total">{{price}}元/平</span>
                	<span class="unit-price">{{acreage}}平米</span>
                </div>
            </div>
        </div>
	</li>
	{{/each}}
</script>
</body>
</html>