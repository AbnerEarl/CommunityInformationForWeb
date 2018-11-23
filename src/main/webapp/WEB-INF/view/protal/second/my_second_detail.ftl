<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>宝贝详情</title>
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
    //评论
    $(".sec_bth").click(function(){
        $("#post_modal").fadeIn(300)
        $("#secone_modal").fadeOut(300)
    });
    //返回评论列表
    $("#icon_return").click(function(){
        $("#post_modal").fadeOut(300)
        $("#secone_modal").fadeIn(300)
    });
    
    loadAsk()
    
});

function loadAsk(){
	load.get("/protal/second/ask/list?secondId=" + $("#secondId").val(), false, function(data){
		if(data){
			handlebars.loadTemplate("#detail_ask", "#ask-template", data);
		}
	});
}

function askSubmit(){
	if($("#content").val().length == 0){
		tip.msg("请输入回复内容");
		return false;
	}
	load.post("/protal/second/ask/add", {secondId: $("#secondId").val(), content: $("#content").val()} ,true, function(data){
		$("#content").val("");
		if(data.status == 0){
			loadAsk();
			tip.msg(data.msg);
		}else {
			tip.msg(data.msg);
		}
	});
}

function submitBtn(){
	var postData = {
		id: $("#newId").data("value"),
		status: 1
	}
	tip.confirm("确认下架？", function(){
		load.post("/protal/second/update", postData, true, function(data){
	        if (data.status == 0) {
	        	tip.msg("取消成功");
	            window.location.href = "/protal/second/second/my";
	        } else {
	            tip.msg("取消失败");
	        }
		});
	});
}
</script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="/protal/second/second" class="return"><i class="icon-chevron-left"></i> 返回</a>
               商品详情
        <a href="#" class="search" onclick="submitBtn()">下架</a>
    </div>
    <div class="banner">
        <div class="swipe">
            <ul id="slider">
                <#list second.pictureList as item>
                <li>
                    <a href="#"><img src="${item}" height="180" alt="" /></a>
                </li>
                </#list>
            </ul>
            <div id="pagenavi">
            	<#list second.pictureList as item>
                <a href="javascript:void(0);" class=""></a>
                </#list>
            </div>
        </div>
    </div>
    <div class="main" id="newId" data-value="${second.id}">
        <div class="postall margin_top clear_border">
            <a href="#">
                <div class="second" style="margin-bottom: 1rem;">
                    <span class="puff_left color_y">￥${second.priceName}</span>
                    <span class="puff_right">
	                    <label><i class="icon-certificate"></i> ${second.categoryName}</label>
	                    <label><i class="icon-backward"></i> ${second.degreeName}</label>
	                </span>
                </div>
                <div class="post_t">
                    <img src="${second.userAvatar}">
                    <span>${second.userName}</span>
                    <label class="puff_right">${second.dealTime}</label>
                </div>
                <div class="post_m">
                    <p>${second.content}</p>
                </div>
                <div class="second">
                    <span class="puff_left">联系电话：<font>${second.linkphoneTo}</font></span>
                    <span class="puff_right">联系人：<font>${second.linkman}</font></span>
                </div>
                <div class="second clear_border">
                    <span class="puff_left">联系QQ：<font>${second.linkqq}</font></span>
                </div>
            </a>
        </div>
    </div>
    <div class="main" style="margin-top: 1rem;" id="detail_ask">
    </div>

</div>
<div class="secondnav" id="secone_modal">
    <ul>
        <li class="animated fadeInLeft">
            <a class="sec_bth">
                <i class="icon-comment-alt"> 评论</i>
            </a>
        </li>
        <li class="animated fadeInLeft">
            <a href="mqqwpa://im/chat?chat_type=wpa&uin=${second.linkqq}&version=1&src_type=web&web_src=oicqzone.com">
                <i class="icon-user-md"> QQ</i>
            </a>
        </li>
        <li class="clear_border animated fadeInLeft">
            <a href="tel://${second.linkphone}">
                <i class="icon-phone"> 联系电话</i>
            </a>
        </li>
        <li class="clear_border animated fadeInLeft">
            &nbsp;&nbsp;
        </li>
    </ul>
</div>
<div class="postinput animated bounceIn dis_none"  id="post_modal">
    <a id="icon_return"><i class="icon-reply"></i> </a>
    <input type="hidden" id="secondId" value="${second.id}">
    <input type="text" id="content" placeholder="说些什么">
    <a href="#" onclick="askSubmit()"><i class="icon-circle-arrow-right"></i> </a>
</div>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/slide_wap.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/common.js"></script>
<script id="ask-template" type="text/x-handlebars-template">
	{{#each this}}
    <div class="postall">
        <div class="post_t">
            <img src="{{userAvatar}}">
            <span>{{userName}}</span>
            <label class="puff_right">{{dealTime}}</label>
        </div>
        <div class="post_m">
            <p class="animated fadeInRight">{{content}}</p>
        </div>
    </div>
	{{/each}}
</script>
</body>
</html>