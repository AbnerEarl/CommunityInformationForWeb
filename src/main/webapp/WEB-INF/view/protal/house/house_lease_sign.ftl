<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>发布出租房</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/shake.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/pickout.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/pickout.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script>
$(function(){
	$("#pictures").val("");
})

var picArray = new Array();
function uploadFile(){
	tip.loading();
    $.ajaxFileUpload({
        url: "/file/protal/upload",
        secureuri: false,
        type:'POST',
        dataType: "JSON",
        fileElementId: "uploadFile",
        success: function (data, status) {
        	tip.closeAll();
			var obj = JSON.parse(data);
			if(obj.error == 0){
				picArray.push(obj.url);
				showImg();
			}else {
	        	tip.msg(obj.message);
			}
        },
        error: function (data, xhr) {
       	    tip.closeAll();
        	tip.msg("操作失败，原因可能是:" + xhr.statusText);
        }
    });
}

function showImg(){
	$("#ul_pictures").html("");
	var html = "";
	$.each(picArray, function(i, pic) {
		var url = pic + "?r=" + Math.random();
		html += "<li><a href='#'>";
		html += "<img src='" + url + "' />";
		html += "</a></li>";
	});
	$("#ul_pictures").append(html);
	$("#pictures").val(picArray.join(","));
}

function submitBtn(){
	if($("#pictures").val().length == 0){
		tip.msg("请上传图片");
		return false;
	}
	if($("#title").val().length == 0){
		tip.msg("请输入房屋标题");
		return false;
	}
	if($("#countRoom").val().length == 0){
		tip.msg("请输入卧室数量");
		return false;
	}
	if($("#counToilet").val() == 0){
		tip.msg("请输入卫生间数量");
		return false;
	}
	if($("#countHall").val() == 0){
		tip.msg("请输入厅数量");
		return false;
	}
	if($("#decoration").val() == 0){
		tip.msg("请选择装修情况");
		return false;
	}
	if($("#acreage").val() == 0){
		tip.msg("请输入面积");
		return false;
	}
	if($("#price").val().length == 0){
		tip.msg("请输入单价");
		return false;
	}
	if($("#purpose").val() == 0){
		tip.msg("请选择用途");
		return false;
	}
	if($("#address").val() == 0){
		tip.msg("请选择地址");
		return false;
	}
	if($("#linkman").val().length == 0){
		tip.msg("请输入联系人");
		return false;
	}
	if($("#linkphone").val().length == 0){
		tip.msg("请输入联系电话");
		return false;
	}
	if($("#linkqq").val().length == 0){
		tip.msg("请输入QQ号码");
		return false;
	}
	if($("#remarks").val().length == 0){
		tip.msg("请输入房屋详情");
		return false;
	}
	var postData = {
		pictures: $("#pictures").val(),
		title: $("#title").val(),
		remarks: $("#remarks").val(),
		countRoom: $("#countRoom").val(),
		countToilet: $("#countToilet").val(),
		countHall: $("#countHall").val(),
		acreage: $("#acreage").val(),
		price: $("#price").val(),
		address: $("#address").val(),
		linkman: $("#linkman").val(),
		linkphone: $("#linkphone").val(),
		linkqq: $("#linkqq").val()
	}
	tip.confirm("确认发布出租房？", function(){
		load.post("/protal/house/lease/add", postData, true, function(data){
	        if (data.status == 0) {
	        	tip.msg(data.msg);
	            window.location.href = "/protal/house/lease";
	        } else {
	            tip.msg(data.msg);
	        }
		});
	});
}
</script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="#" class="return"><i class="icon-chevron-left"></i> 返回</a>
        发布出租房
        <a href="#" class="search" onclick="submitBtn()">提交</a>
    </div>
    <div class="main">
        <div class="edit">
            <div class="edit_b">
            	<ul id="ul_pictures" class="margin_left"></ul>
                <ul>
                    <li>
                        <a href="#" class="add_img">+
                            <input id="uploadFile" name="uploadFile" type="file" onchange="uploadFile()" accept="image/*;capture="camera" multiple />
                        	<input type="hidden" id="pictures" />
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="edit_t">
        <textarea rows="3" id="title" placeholder="描述一下房屋的卖点吧！"></textarea>
    </div>
    <div class="part part2">
        <ul>
            <li>
                <input type="text" id="countRoom" placeholder="卧室数量">
                <label class="puff_left">卧室：</label>
            </li>
            <li>
                <input type="text" id="countHall" placeholder="大厅数量">
                <label class="puff_left">大厅：</label>
            </li>
            <li>
                <input type="text" id="countToilet" placeholder="卫生间数量">
                <label class="puff_left">卫生间：</label>
            </li>
            <li>
                <input type="text" id="acreage" placeholder="房屋面积">
                <label class="puff_left">面积：</label>
            </li>
            <li>
                <input type="text" id="price" placeholder="房屋预计房租">
                <label class="puff_left">房租：</label>
            </li>
        </ul>
    </div>
    <div class="edit_t">
        <textarea rows="3" id="address" placeholder="请输入房屋的详细地址！"></textarea>
    </div>
    <div class="part part2">
        <ul>
            <li>
                <input type="text" id="linkman" placeholder="您的姓名">
                <label class="puff_left">联系人：</label>
            </li>
            <li>
                <input type="text" id="linkphone" placeholder="您的联系电话">
                <label class="puff_left">联系手机：</label>
            </li>
            <li>
                <input type="text" id="linkqq" placeholder="您的QQ号码">
                <label class="puff_left">联系QQ：</label>
            </li>
        </ul>
    </div>
    <div class="edit_t">
        <textarea rows="5" id="remarks" placeholder="描述一下房屋的详细信息吧！"></textarea>
    </div>
</div>
</body>
</html>