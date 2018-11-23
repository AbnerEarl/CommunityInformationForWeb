<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>跳蚤市场</title>
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
    $("#second_btn01").click(function(){
        $("#secondlist").toggle();
    });
    
    $("#second_btn02").click(function(){
        $("#secondlist2").toggle();
    });
    
    $("#secondlist a").click(function(){
    	$("#secondlist").data("value", $(this).data("value"));
        $("#second_btn01").find("font").html($(this).text());
        $("#secondlist").toggle();
        loadData();
    });
    
    $("#secondlist2 a").click(function(){
    	$("#secondlist2").data("value", $(this).data("value"));
        $("#second_btn02").find("font").html($(this).text());
        $("#secondlist2").toggle();
        loadData();
    });  
        
    loadData();
});

function loadData(){
	load.get("/protal/second/list?category=" + $("#secondlist").data("value") + "&price=" + $("#secondlist2").data("value"), false, function(data){
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
        <a href="/protal/index" class="return"><i class="icon-chevron-left"></i> 返回</a>
       	 跳蚤市场
        <a href="/protal/second/sign" class="search"><i class="icon-edit"></i> </a>
    </div>
    <div class="my_tab">
        <a href="#" hidefocus="true" id="second_btn01"><font>不限类别</font> <span class="icon-chevron-down"></span></a>
            <div class="secondlist animated fadeInRight dis_none"  data-value="-1" id="secondlist">
                <ul>
                	<li>
                        <a href="#" class="animated rubberBand" data-value="-1">不限类别</a>
                    </li>
                    <li>
                        <a href="#" class="animated rubberBand" data-value="1">家具</a>
                    </li>
                    <li>
                        <a href="#" class="animated rubberBand" data-value="2">电器</a>
                    </li>
                    <li>
                        <a href="#" class="animated rubberBand" data-value="3">杂物</a>
                    </li>
                    <li>
                        <a href="#" class="animated rubberBand" data-value="4">其他</a>
                    </li>
                </ul>
            </div>
        <a href="#" hidefocus="true" id="second_btn02"><font>不限价格</font> <span class="icon-chevron-down"></span></a>
        <div class="secondlist secondlist2 animated fadeInRight dis_none" data-value="-1" id="secondlist2">
            <ul>
            	<li>
                    <a href="#" class="animated rubberBand" data-value="-1">不限价格</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="1">面议</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="2">交换同价值物品</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="3">0-200元</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="4">200-500元</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="5">500-1000元</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="6">1000-5000元</a>
                </li>
                <li>
                    <a href="#" class="animated rubberBand" data-value="7">5000元以上</a>
                </li>
            </ul>
        </div>
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
	    <a href="/protal/second/detail/{{id}}">
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