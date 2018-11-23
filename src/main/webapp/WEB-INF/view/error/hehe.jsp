function editFarmSubmit(){
	if($("#txtEditName").val().length == 0){
		tip.msg("请输入农场名称");
		return false;
	}
	if($("#txtEditEditress").val().length == 0){
		tip.msg("请输入农场地址");
		return false;
	}
	if($("#txtEditLinkman").val().length == 0){
		tip.msg("请输入联系人");
		return false;
	}
	if($("#txtEditLinkphone").val().length == 0){
		tip.msg("请输入联系电话");
		return false;
	}
	if($("#txtEditRemarks").val().length == 0){
		tip.msg("请输入农场详情");
		return false;
	}
    var postData = {
        name: $("#txtEditName").val(),
		Editress: $("#txtEditEditress").val(),
		linkman: $("#txtEditLinkman").val(),
		linkphone: $("#txtEditLinkphone").val(),
		remarks: $("#txtEditRemarks").val()
	};
	load.post("/farm/Edit", postData, true, function(data){
        if(data.status == 0){
        	loEditata();
        	EditUserCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}