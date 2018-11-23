<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="always" name="referrer"> 
<title>欢迎登录后台管理平台</title>  
<link href="${request.contextPath}/static/content/css/common/login.css" rel="stylesheet" type="text/css"/>
<script src="${request.contextPath}/static/script/util/jquery/jquery.min.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/script/util/layer/layer.js" type="text/javascript"></script> 
<script src="${request.contextPath}/static/script/other/cloud.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/script/common/common.js" type="text/javascript"></script>
<script src="${request.contextPath}/static/js/home/login.js" type="text/javascript"></script>
</head>
<body>
<div id="mainBody" class="mainBody"> 
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>   
</div>      
<div class="logintop">
    <span>欢迎登录后台管理系统</span>
    <ul> 
        <li><a href="javascript:void(0);">帮助</a></li>
        <li><a href="javascript:void(0);">关于</a></li>
    </ul>
</div>      
<div class="loginbody">
    <span class="systemlogo"></span> 
    <div class="loginbox loginbox2">
        <ul>
            <li><input id="username" type="text" class="loginuser" placeholder="用户名"/></li>
            <li><input id="password" type="password" class="loginpwd" placeholder="密码"/></li>
            <li class="yzm">
                <span><input id="kaptcha" type="text" placeholder="验证码"/></span>
                <cite>
                    <img alt="验证码" src="/file/kaptcha" title="点击更换" id="img_captcha"
                        onclick="javascript:refreshCaptcha();" class="img_captcha"/>
                </cite>
            </li>
            <li>
                <input type="button" class="loginbtn" value="登录" onclick="javascript:validateSubmit();"/>
                <label><input id="rememberMe" type="checkbox" checked="checked"/>记住密码</label>
            </li>
        </ul>
    </div>
</div>
<div class="loginbm">Copyright ©2018
    <a href="http://www.***.**">www.***.**</a>
</div>
</body>
</html>
