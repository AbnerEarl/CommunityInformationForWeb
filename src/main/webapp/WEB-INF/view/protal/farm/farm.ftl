<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>农场</title>
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
})

function loadData(){
	load.post("/protal/farm/list", {}, false, function(data) {
		if(data){
			handlebars.loadTemplate("#farm-list", "#list-template", data);
		}
	});
}
</script>
</head>
<body>
<div class="head">
    <a href="/protal/index" class="return"><i class="icon-chevron-left"></i> 返回</a>
   	 农场资讯
</div>
<ul class="house-list" id="farm-list">
</ul>
<script id="list-template" type="text/x-handlebars-template">
	{{#each this}}
	<li>
		<div class="con">
            <div class="house-pic animated fadeInLeft"><img src="{{listFirst pictures}}"></div>
            <div class="house-detail">
                <h3 class="animated fadeInRight">{{name}}</h3>
                <div class="house-price">
                	<br><br>
                	<a href="/protal/farm/product/{{id}}" class="price-total">进入农场</a>
                	<a href="/protal/farm/detail/{{id}}" class="unit-price">查看详情</a>
                </div>
            </div>
        </div>
	</li>
	{{/each}}
</script>
</body>
</html>