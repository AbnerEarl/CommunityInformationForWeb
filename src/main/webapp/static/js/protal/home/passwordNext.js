function submitBtn() {
	if ($("#password").val().length == 0) {
		tip.msg("请输入密码");
		return false;
	}
	if ($("#confirmPass").val().length == 0) {
		tip.msg("请输入确认密码");
		return false;
	} else if ($("#confirmPass").val() != $("#password").val()) {
		tip.msg("两次密码输入不一致");
		return false;
	}
	var postData = {
		id : $("#userId").val(),
		password : $("#password").val()
	}
	load.post("/protal/resetPassFilish", postData, true, function(data) {
		if (data.status == 0) {
			tip.msg(data.msg);
			window.location.href = "/protal/login";
		} else {
			tip.msg(data.msg);
		}
	});
}