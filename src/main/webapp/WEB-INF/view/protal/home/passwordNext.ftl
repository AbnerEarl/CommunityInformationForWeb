<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>忘记密码</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/protal/home/passwordNext.js"></script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="#" class="return"><i class="icon-chevron-left"></i> 返回</a>
        忘记密码
    </div>
    <div class="main">
        <div class="regall">
            <div class="regTxt">
                <p class="color_r animated bounceInLeft">请设置您的新登录密码</p>
                <input type="hidden" id="userId" value="${userId}">
            </div>
            <div class="reginput">
                <input type="password" id="password" placeholder="请输入6~20位新密码" class="animated rotateInUpLeft">
            </div>
            <div class="reginput">
                <input type="password" id="confirmPass" placeholder="请再次输入" class="animated rotateInUpLeft">
            </div>
            <div class="id_bth inersest_bth animated bounceIn" style="margin-top: 2rem;">
                <a href="#" onclick="submitBtn()">完 成</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>