$(function () {
    $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
    $(window).resize(function () {
        $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
    });
    $("#username").focus();
	$("#username").keydown(function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			e.preventDefault();
			$("#password").focus(); 
		}
	});
	$("#password").keydown(function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			e.preventDefault(); 
			$("#kaptcha").focus();
		} 
	});   
	$("#kaptcha").keydown(function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			e.preventDefault();
			validateSubmit();  
		}
	});
});

/**
 * 点击刷新验证码
 */  
function refreshCaptcha() {
    $('#img_captcha').attr('src', '/file/kaptcha?t=' + Math.floor(Math.random() * 100));
}

/**
 * 登录系统 
 */ 
function validateSubmit() {
	var username = $('#username').val().trim();
    var password = $('#password').val().trim();
    var kaptcha = $('#kaptcha').val().trim();
    if (validate.isEmpty(username)) {
        tip.tips('请输入用户名', '#username'); 
        return false;
    }
    if (validate.isEmpty(password)) {
        tip.tips('请输入密码', '#password');
        return false; 
    }
    if (validate.isEmpty(kaptcha)) {
        tip.tips('请输入验证码', '.yzm');
        return false;
    }  
    var postData = {
        username: username,
        password: password, 
        kaptcha: kaptcha, 
        remember: $('#rememberMe').is(':checked')	
    }
    load.post("/userLogin", postData, true, function(data){ 
        if (data.status == 0) {
            tip.msg('登录成功');
            window.location.href = "/home";
        } else {
            tip.msg(data.msg);
            refreshCaptcha();
        }
    });
}