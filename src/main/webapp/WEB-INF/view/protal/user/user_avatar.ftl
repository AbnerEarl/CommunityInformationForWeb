<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>昵称</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script type="text/javascript">
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
				$("#avatar").parent().find('img').remove();
				$("#avatar").val(obj.url);
				var url = obj.url + "?r=" + Math.random();
				$("#avatar").before("<img src='" + url + "' />");
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

function submitBtn(){
	if($("#avatar").val().length == 0){
		tip.msg("请上传您的头像");
		return false;
	}
	tip.confirm("确认修改头像？", function(){
		load.post("/protal/user/update", {avatar:$("#avatar").val()}, true, function(data){
	        if (data.status == 0) {
	        	tip.msg(data.msg);
	            window.location.href = "/protal/user/admin";
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
       	修改头像
        <a href="#" class="search animated fadeInRight" onclick="submitBtn()">提交</a>
    </div>
    <div class="main">
        <div class="edit">
            <div class="edit_b">
                <ul>
                    <li class="margin_left">
                        <a href="#">
                            <img src="${avatar}">
                            <input type="hidden" id="avatar" />
                        </a>
                    </li>
                    <li>
                        <a href="#" class="add_img">+
                            <input id="uploadFile" name="uploadFile" type="file" onchange="uploadFile()" accept="image/*;capture="camera" />
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>