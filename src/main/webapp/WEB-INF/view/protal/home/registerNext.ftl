<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>用户注册</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/protal/home/registerNext.js"></script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="#" class="return"><i class="icon-chevron-left"></i> 返回</a>
   	      用户注册
    </div>
    <div class="main">
        <div class="part part2 margin_top">
            <ul>
            	<li class="animated fadeInRight">
                    <input type="hidden" id="phone" value="${phone}">
                    <input type="text" id="username" placeholder="社区登录帐号" class="text_r">
                    <label class="puff_left">用户名：</label>
                </li>
                <li class="animated fadeInRight">
                    <input type="text" id="name" placeholder="方便社区交流" class="text_r">
                    <label class="puff_left">姓名：</label>
                </li>
                <li class="animated fadeInRight">
                    <input type="text" id="idcard" placeholder="用于身份认证" class="text_r">
                    <label class="puff_left">身份证：</label>
                </li>
                <li class="animated fadeInRight">
                    <input type="text" id="address" placeholder="社区居住地址" class="text_r">
                    <label class="puff_left">居住地址：</label>
                </li>
                <li class="animated fadeInLeft">
                    <div class="radio_bth">
                        <label><input type="radio" name="sex" value="1" checked>男</label>
                        <label><input type="radio" name="sex" value="2">女</label>
                    </div>
                    <label class="puff_left">性别：</label>
                </li>
                <li class="animated fadeInLeft">
                    <input type="password" id="password" placeholder="6~20位数字英文组合" class="text_r">
                    <label class="puff_left">设置密码：</label>
                </li>
                <li class=" animated fadeInRight">
                    <input type="password" id="confirmPass" placeholder="请再次输入" class="text_r">
                    <label class="puff_left">确认密码：</label>
                </li>
            </ul>
        </div>
    </div>
    <div class="id_bth inersest_bth animated bounceIn">
        <a href="#" onclick="submitBtn()">提 交</a>
    </div>
</div>
</body>
</html>