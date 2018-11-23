function submitBtn(){
	if($("#username").val().length == 0){
		tip.msg("请输入用户名");
		return false;
	}
	if($("#name").val().length == 0){
		tip.msg("请输入姓名");
		return false;
	}
	if($("#idcard").val().length == 0){
		tip.msg("请输入身份证");
		return false;
	}else if(validate.idCard($("#idcard").val())){
		tip.msg("请输入正确的身份证");
		return false;
	}
	if($("#address").val().length == 0){
		tip.msg("请输入居住地址");
		return false;
	}
	if($("#password").val().length == 0){
		tip.msg("请输入密码");
		return false;
	}else if($("#confirmPass").val() != $("#password").val()){
		tip.msg("两次密码输入不一致");
		return false;
	}
	var postData = {
		phone:$("#phone").val(),
		username:$("#username").val(),
		name:$("#name").val(),
		address:$("#address").val(),
		idcard:$("#idcard").val(),
		sex:$("input[name=sex]:checked").val(),
		password: $("#password").val()
	}
	tip.confirm("请确保姓名，身份证及居住地址与社区管理员处登记情况一致，否则将无法被认证，结果将导致很多功能受到限制，请谨慎填写！", function(){
		load.post("/protal/registerFilish", postData, true, function(data){
			if (data.status == 0) {
				window.location.href = "/protal/login";
			} else {
				tip.msg(data.msg);
			}
		});
	})
}