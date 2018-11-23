<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>发布商品</title>
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
	pickout.to({
		el:'#category',
		theme: 'clean'
	});
	pickout.updated('#category');  
	pickout.to({
		el:'#price',
		theme: 'clean'
	});
	pickout.updated('#price'); 
	pickout.to({
		el:'#degree',
		theme: 'clean'
	});
	pickout.updated('#degree'); 
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
	if($("#content").val().length == 0){
		tip.msg("请输入商品描述");
		return false;
	}
	if($("#category").val() == 0){
		tip.msg("请选择商品类别");
		return false;
	}
	if($("#price").val().length == 0){
		tip.msg("请选择商品价值");
		return false;
	}
	if($("#degree").val() == 0){
		tip.msg("请选择新旧程度");
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
	var postData = {
		pictures: $("#pictures").val(),
		content: $("#content").val(),
		category: $("#category").val(),
		price: $("#price").val(),
		degree: $("#degree").val(),
		linkman: $("#linkman").val(),
		linkphone: $("#linkphone").val(),
		linkqq: $("#linkqq").val()
	}
	tip.confirm("确认发布商品？", function(){
		load.post("/protal/second/add", postData, true, function(data){
	        if (data.status == 0) {
	        	tip.msg(data.msg);
	            window.location.href = "/protal/second/second";
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
        发布商品
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
                        	<input type="hidden" id="pictures" value="" />
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
        <div class="edit_t">
        <textarea rows="5" id="content" placeholder="描述一下想发布倒卖的商品信息吧！"></textarea>
    </div>
    <div class="part part2">
        <ul>
            <li>
				<select id="category" class="pickout" placeholder="请选择商品类别" style="display: none;">
					<option value="0" selected>请选择商品类别</option>
					<option value="1">家具</option>
					<option value="2">电器</option>
					<option value="3">杂物</option>
					<option value="4">其他</option>
				</select>
                <label class="puff_left">商品类型：</label>
            </li>
            <li class="clear">
				<select id="price" class="pickout" placeholder="请选择商品价值" style="display: none;">
					<option value="" selected>请选择商品价值</option>
					<option value="1">面议</option>
					<option value="2">交换同价值物品</option>
					<option value="3">0-200元</option>
					<option value="4">200-500元</option>
					<option value="5">500-1000元</option>
					<option value="6">1000-5000元</option>
					<option value="7">5000元以上</option>
				</select>
                <label class="puff_left">商品价值：</label>
            </li>
            <li class="clear">
				<select id="degree" class="pickout" placeholder="请选择新旧程度" style="display: none;">
					<option value="0" selected>请选择新旧程度</option>
					<option value="1">全新</option>
					<option value="2">6-9成新</option>
					<option value="3">5成新</option>
					<option value="4">5成新以下</option>
				</select>
                <label class="puff_left">新旧程度：</label>
            </li>
        </ul>
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
</div>
</body>
</html>