<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>邮箱</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script>
	function submitBtn(){
		if($("#email").val().length == 0){
			tip.msg("请输入您的邮箱");
			return false;
		}else if(validate.email($("#email").val())){
			tip.msg("请输入正确的邮箱");
			return false;
		}
		tip.confirm("确认修改邮箱？", function(){
			load.post("/protal/user/update", {email:$("#email").val()}, true, function(data){
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
    <div class="reveal-modal-bg dis_none"></div>
    <div class="head">
        <a href="#" class="return"><i class="icon-chevron-left"></i> 返回</a>
       	 修改邮箱
        <a href="#" class="search animated fadeInRight" onclick="submitBtn()">提交</a>
    </div>
    <div class="main">
        <div class="regall">
            <div class="animated bounceInRight reginput">
                <input type="text" id="email" placeholder="请输入您的邮箱">
            </div>
        </div>
    </div>
</div>
</body>
</html>