<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>二手房</title>
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
   	 二手房详情
</div>
<div class="banner">
    <div class="swipe">
        <ul id="slider">
            <#list houseOld.pictureList as item>
            <li>
                <a href="#"><img src="${item}" height="180" alt="" /></a>
            </li>
            </#list>
        </ul>
        <div id="pagenavi">
        	<#list houseOld.pictureList as item>
            <a href="javascript:void(0);" class=""></a>
            </#list>
        </div>
    </div>
</div>
<h1 class="detail-title">${houseOld.title}</h1>
<h3 class="similar-data">
	<div class="similar_data_detail">
		<p class="gray big">总价</p>
		<p class="red big"><span>${houseOld.price}万元</span></p>
	</div>
	<div class="similar_data_detail"><p class="gray big">房型</p><p class="red big">${houseOld.countRoom}室${houseOld.countHall}厅${houseOld.countToilet}卫</p></div>
	<div class="similar_data_detail"><p class="gray big">建筑面积</p><p class="red big">${houseOld.acreage}m²</p></div>
 </h3>                   
<ul class="house_description">
    <div class="tag_group">
        <span style="color:rgb(242,161,47);background-color:rgba(242,161,47,0.15);">满五年</span>
        <span style="color:rgb(51,190,133);background-color:rgba(51,190,133,0.15);">随时看房</span>
    </div>
    <li class="short"><span class="gray">总价：</span>${houseOld.price}万元</li>
    <li class="short"><span class="gray">面积：</span>${houseOld.acreage}m²</li>
    <li class="short"><span class="gray">卧室：</span>${houseOld.countRoom}间</li>
    <li class="short"><span class="gray">卫生间：</span>${houseOld.countHall}间</li>
    <li class="short"><span class="gray">大厅：</span>${houseOld.countToilet}间</li>
    <li class="short"><span class="gray">电梯：</span>独栋电梯</li>
    <li class="short"><span class="gray">建造年份：</span>${houseOld.build}</li>
    <li class="short"><span class="gray">装修：</span>${houseOld.decoration}</li>
    <li class="long "><a><span class="gray">房屋地址：</span>${houseOld.address}</a></li>
    <li class="short"><span class="gray">联系人：</span>${houseOld.linkman}</li>
    <li class="short"><a href="mqqwpa://im/chat?chat_type=wpa&uin=${houseOld.linkqq}&version=1&src_type=web&web_src=oicqzone.com"><span class="gray">联系qq：</span>${houseOld.linkqq}<a></li>
    <li class="long "><a href="tel://${houseOld.linkphone}"><span class="gray">联系电话：</span>${houseOld.linkphoneTo}</a></li>
</ul>
<a class="house_introduce_address" href="javascript:;">
    <h3 class="mod_tit">房源介绍</h3>
    <div class="mod_cont house_intro_mod_cont">
		${houseOld.remarks}
    </div>
</a>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/slide_wap.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/common.js"></script>
</body>
</html>