<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>修改密码</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/shake.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script>
	function submitBtn(){
		if($("#oldPass").val().length == 0){
			tip.msg("请输入旧密码");
			return false;
		}
		if($("#newPass").val().length == 0){
			tip.msg("请输入新密码");
			return false;
		}
		if($("#confirmPass").val().length == 0){
			tip.msg("请输入确认新密码");
			return false;
		}else if($("#confirmPass").val() != $("#newPass").val()){
			tip.msg("两次密码输入不一致");
			return false;
		}
		tip.confirm("确认修改密码？", function(){
			load.post("/protal/user/updatePassword", {oldPass:$("#oldPass").val(), newPass:$("#newPass").val()}, true, function(data){
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
        修改密码
        <a href="#" class="search" onclick="submitBtn()">提交</a>
    </div>
    <div class="main">
        <div class="edit_t">
            <input type="password" id="oldPass" placeholder="输入旧密码" class="animated fadeInRight">
            <input type="password" id="newPass" placeholder="设置新密码" class="animated fadeInLeft">
            <input type="password" id="confirmPass" placeholder="确认新密码" class="clear_border animated fadeInRight">
        </div>
    </div>
</div>
</body>
</html>