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
</head>
<body>
<div class="head">
    <a href="#" class="return"><i class="icon-chevron-left"></i> 返回</a>
   	 ${farm.name}
</div>
<div class="banner">
    <div class="swipe">
        <ul id="slider">
            <#list farm.pictureList as item>
            <li>
                <a href="#"><img src="${item}" height="180" alt="" /></a>
            </li>
            </#list>
        </ul>
        <div id="pagenavi">
        	<#list farm.pictureList as item>
            <a href="javascript:void(0);" class=""></a>
            </#list>
        </div>
    </div>
</div>
<ul class="house_description">
    <li class="short"><span class="gray">联系人：</span>${farm.linkman}</li>
    <li class="long "><a href="tel://${farm.linkphone}"><span class="gray">联系电话：</span>${farm.linkphoneTo}</a></li>
    <li class="long "><a><span class="gray">农场地址：</span>${farm.address}</a></li>
</ul>
<a class="house_introduce_address" href="javascript:;">
    <h3 class="mod_tit">农场介绍</h3>
    <div class="mod_cont house_intro_mod_cont">
		${farm.remarks}
    </div>
</a>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/slide_wap.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/common.js"></script>
</body>
</html>