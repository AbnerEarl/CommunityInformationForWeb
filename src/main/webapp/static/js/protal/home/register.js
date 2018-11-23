var countdown = 60;
function send(e) {
	if($("#phone").val().length == 0){
		tip.msg("请输入手机号");
		return false;
	}else if(validate.cellPhone($("#phone").val())){
		tip.msg("请输入正确的手机号");
		return false;
	}
	load.post("/protal/getPhoneRegisterYzm", {phone: $("#phone").val()}, true, function(data){
        if (data.status == 0) {
            settime($(e));
            tip.msg("验证码已发送至您的手机，请及时验证");
        } else {
            tip.msg(data.msg);
        }
	});
}
 
function settime(obj) { 
    if (countdown == 0) {
        obj.removeAttr("disabled");
        obj.html("获取验证码");
        countdown = 60;
        return;
    } else {
        obj.attr('disabled', "true");
        obj.html('重新发送('+ countdown +')');
        countdown--;
    }
    setTimeout(function () {
        settime(obj);
    }, 1000)
}

function submitBtn(){
	if($("#phone").val().length == 0){
		tip.msg("请输入手机号");
		return false;
	}else if(validate.cellPhone($("#phone").val())){
		tip.msg("请输入正确的手机号");
		return false;
	}
	if($("#yzm").val().length == 0){
		tip.msg("请输入验证码");
		return false;
	}
	var postData = {
		phone:$("#phone").val(),
		yzmCode: $("#yzm").val()
	}
	load.post("/protal/registerOne", postData, true, function(data){
        if (data.status == 0) {
        	var phone = data.data;
            window.location.href = "/protal/registerNext?phone=" + phone;
        } else {
            tip.msg(data.msg);
        }
	});
}