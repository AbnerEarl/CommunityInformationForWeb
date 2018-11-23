<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>智慧社区</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/protal/home/login.js"></script>
</head>
<body>
<div class="warpe">
    <div class="head">智慧社区</div>
    <div class="main">
        <div class="regall">
            <div class="reginput">
                <input type="text" id="username" placeholder="请输入用户名" class="animated rotateInUpLeft">
            </div>
            <div class="reginput">
                <input type="password" id="password" placeholder="请输入密码" class="animated rotateInUpLeft">
            </div>
            <div class="id_bth inersest_bth animated bounceIn" style="margin-top: 2rem;">
                <a href="#" onclick="submitBtn()">登 录</a>
            </div>
            <div class="regTxt">
                <p>
                	<a href="/protal/register" class="puff_left animated bounceInLeft">注册账号</a> 
                	<a href="/protal/password" class="puff_right animated bounceInRight">忘记密码</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>