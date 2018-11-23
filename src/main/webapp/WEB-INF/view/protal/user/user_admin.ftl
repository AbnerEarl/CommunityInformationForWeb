<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>账户管理</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="/protal/user/user" class="return"><i class="icon-chevron-left"></i> 返回</a>
        账户管理
    </div>
    <div class="inter_add">
        <ul>
            <li class="animated bounceInLeft">
                <a href="/protal/user/avatar">
                    <span class="puff_left">头像</span>
                    <span class="puff_right"><img src="${avatar}"> </span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInRight">
                <a href="#">
                    <span class="puff_left">用户名</span>
                    <span class="puff_right"><font>${username}</font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInRight">
                <a href="#">
                    <span class="puff_left">姓名</span>
                    <span class="puff_right"><font>${name}</font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInLeft">
                <a href="/protal/user/sex">
                    <span class="puff_left">性别</span>
                    <span class="puff_right"><font>${(sex==1)?string('男','女')}</font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInRight">
                <a href="#">
                    <span class="puff_left">身份证</span>
                    <span class="puff_right"><font>${idcard}</font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInRight">
                <a href="#">
                    <span class="puff_left">地址</span>
                    <span class="puff_right"><font>${address}</font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInLeft clear_border">
                <a href="/protal/user/email">
                    <span class="puff_left">邮箱</span>
                    <span class="puff_right"><font>${email}</font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
        </ul>
    </div>
    <div class="inter_add">
        <ul>
            <li class="animated bounceInLeft clear_border">
                <a href="/protal/user/password">
                    <span class="puff_left">修改密码</span>
                    <span class="puff_right"><font></font></span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>