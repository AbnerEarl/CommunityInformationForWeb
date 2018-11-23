<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>我的发布</title>
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
<script type="text/javascript">
$(document).ready(function(){
    loadData();
});

function loadData(){
	load.get("/protal/second/my/list", false, function(data){
		if(data){
			handlebars.loadTemplate("#searchlist", "#list-template", data);
		}
	});
}
</script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="/protal/user/user" class="return"><i class="icon-chevron-left"></i> 返回</a>
       	 我的发布
    </div>
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <div class="content-slide">
                    <div class="main" id="searchlist">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script id="list-template" type="text/x-handlebars-template">
	{{#each this}}
	<div class="postall">
	    <a href="/protal/second/my/detail/{{id}}">
	        <div class="post_t">
	            <img src="{{userAvatar}}">
	            <span>{{userName}}</span>
	            <span class="puff_right place animated fadeInRight"><label class="color_y">￥{{priceName}}</label> </span>
	        </div>
	        <div class="post_m">
	            <p>{{content}}</p>
	        </div>
	        <div class="post_img">
	            <ul class="postimg_list2">
	            	{{#each pictureList}}
	                <li class="animated bounceIn">
	                    <img src="{{this}}">
	                </li>
	                {{/each}}
	            </ul>
	        </div>
	        <div class="post_b">
	            <span class="puff_left">{{dealTime}}</span>
                <span class="puff_right">
                    <label><i class="icon-certificate"></i> {{categoryName}}</label>
                    <label><i class="icon-backward"></i> {{degreeName}}</label>
                </span>
	        </div>
	    </a>
	</div>
	{{/each}}
</script>
</body>
</html>