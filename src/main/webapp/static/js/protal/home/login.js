function submitBtn(){
	if($("#username").val().length == 0){
		tip.msg("请输入用户名");
		return false;
	}
	if($("#password").val().length == 0){
		tip.msg("请输入密码");
		return false;
	}
	var postData = {
		username:$("#username").val(),
		password:$("#password").val()
	}
	load.post("/protal/userLogin", postData, true, function(data){
        if (data.status == 0) {
            tip.msg('登录成功');
            window.location.href = "/protal/index";
        } else {
            tip.msg(data.msg);
        }
	});
}