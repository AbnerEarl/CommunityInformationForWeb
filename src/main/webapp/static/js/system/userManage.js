$(function(){
	$("#txtRole, #txtEditRole,#txtAddRole").FillOptions("/role/getRoleListByParam", {
		valuefiled: "id",
		textfield: "name"
	});
	$("#txtRole").AddOption("请选择", -1, true, 0);
	$("#txtAddRole").AddOption("请选择", -1, true, 0);
})

function loadData(){
	$('#dg').datagrid({  
   	    url: '/user/list?condition=' + $("#txtCondition").val() + '&roleId=' + $("#txtRole").val() + '&status=' + $("#txtStatus").val()
	}); 
}

function FormatterStatus(value, rowData, rowIndex) {
	if(value == 0){
		return "可用";
	}else if(value == 1){
		return "<font color='red'>不可用</font>";
	}else if(value == 2){
		return "<font color='blue'>未认证</font>";
	}
}

function FormatterSex(value, rowData, rowIndex) {
	if(value == 1){
		return "男";
	}else if(value == 2){
		return "女";
	}
}

function addUser(){
	$("#txtAddUsername").val('');
	$("#txtAddName").val('');
	$("#txtAddSex").val(-1);
	$("#txtAddAddress").val('');
	$("#txtAddPhone").val('');
	$("#txtAddEmail").val('');
	$("#txtAddIdcard").val('');
	$("#txtAddRole").val(-1);
	$("#txtAddStatus").val(-1);
	$("#divAdd").dialog('open');
}

function addUserCancal(){
	$("#divAdd").dialog('close');
}

function addUserSubmit(){
	if($("#txtAddUsername").val().length == 0){
     	tip.tips("用户名不能为空", "#txtAddUsername");
		return false;
	}
	if($("#txtAddName").val().length == 0){
     	tip.tips("姓名不能为空", "#txtAddName");
		return false;
	}
	if($("#txtAddSex").val() == -1){
     	tip.tips("请选择性别", "#txtAddSex");
		return false;
	}
	if($("#txtAddIdcard").val().length == 0){
		tip.tips("请输入身份证号码", "#txtAddIdcard");
		return false;
	}else if(validate.idCard($("#txtAddIdcard").val())){
		tip.tips("请输入正确的身份证号码", "#txtAddIdcard");
		return false;
	}
	if($("#txtAddPhone").val().length == 0){
		tip.tips("请输入手机号码", "#txtAddPhone");
		return false;
	}else if(validate.cellPhone($("#txtAddPhone").val())){
		tip.tips("请输入正确的手机号码", "#txtAddPhone");
		return false;
	}
	if($("#txtAddEmail").val().length == 0){
		tip.tips("请输入邮箱", "#txtAddEmail");
		return false;
	}else if(validate.email($("#txtAddEmail").val())){
		tip.tips("请输入正确的邮箱", "#txtAddEmail");
		return false;
	}
	if($("#txtAddRole").val() == -1){
		tip.tips("请选择角色", "#txtAddRole");
		return false;
	}
	if($("#txtAddStatus").val() == -1){
		tip.tips("请选择用户状态", "#txtAddStatus");
		return false;
	}
    var postData = {
		username: $("#txtAddUsername").val(),
        name: $("#txtAddName").val(),
		sex: $("#txtAddSex").val(),
		address: $("#txtAddAddress").val(),
		phone: $("#txtAddPhone").val(),
		email: $("#txtAddEmail").val(),
		idcard: $("#txtAddIdcard").val(),
		roleId: $("#txtAddRole").val(),
		status: $("#txtAddStatus").val()
	};
	load.post("/user/add", postData, true, function(data){
        if(data.status == 0){
        	loadData();
        	addUserCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function editUser(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要编辑的数据");
        return false;
    }
	$("#txtEditUsername").val(rows[0].username);
	$("#txtEditName").val(rows[0].name);
	$("#txtEditSex").val(rows[0].sex);
	$("#txtEditAddress").val(rows[0].address);
	$("#txtEditPhone").val(rows[0].phone);
	$("#txtEditEmail").val(rows[0].email);
	$("#txtEditIdcard").val(rows[0].idcard);
	$("#txtEditRole").val(rows[0].roleId);
	$("#txtEditStatus").val(rows[0].status);
	$("#divEdit").dialog('open');
}

function editUserCancal(){
	$("#divEdit").dialog('close');
}

function editUserSubmit(){
	if($("#txtEditName").val().length == 0){
     	tip.tips("姓名不能为空", "#txtEditName");
		return false;
	}
	if($("#txtEditIdcard").val().length == 0){
		tip.tips("请输入身份证号码", "#txtEditIdcard");
		return false;
	}else if(validate.idCard($("#txtEditIdcard").val())){
		tip.tips("请输入正确的身份证号码", "#txtEditIdcard");
		return false;
	}
	if($("#txtEditPhone").val().length == 0){
		tip.tips("请输入手机号码", "#txtEditPhone");
		return false;
	}else if(validate.cellPhone($("#txtEditPhone").val())){
		tip.tips("请输入正确的手机号码", "#txtEditPhone");
		return false;
	}
	if($("#txtEditEmail").val().length == 0){
		tip.tips("请输入邮箱", "#txtEditEmail");
		return false;
	}else if(validate.email($("#txtEditEmail").val())){
		tip.tips("请输入正确的邮箱", "#txtEditEmail");
		return false;
	}
	var rows = $("#dg").datagrid('getChecked');
    var postData = {
        id: rows[0].id,
		username: $("#txtEditUsername").val(),
        name: $("#txtEditName").val(),
		sex: $("#txtEditSex").val(),
		address: $("#txtEditAddress").val(),
		phone: $("#txtEditPhone").val(),
		email: $("#txtEditEmail").val(),
		idcard: $("#txtEditIdcard").val(),
		roleId: $("#txtEditRole").val(),
		status: $("#txtEditStatus").val()
	};
	load.post("/user/update", postData, true, function(data){
        if(data.status == 0){
        	loadData();
        	editUserCancal();
        }
        tip.msg(data.msg);
	});
}

function resetPass(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length == 0) {
        tip.msg("请选择要重置密码的数据");
        return false;
    }
	tip.confirm('您确定要重置密码吗？', function(){
		var ids = "";
		$.each(rows, function(index, row){
			ids = ids + row.id + ",";
		});
		ids = ids.substring(0, ids.length-1);
		load.post('/user/resetPassword', {ids: ids}, true, function(data){
			if(data.status == 0){
				loadData();
				tip.msg(data.msg);
			}else {
				tip.msg(data.msg);
			}
		});
	});
}

function confirmUser(){
	var rows = $("#dg").datagrid('getChecked');
	if (rows.length != 1) {
		tip.msg("请选择要认证的用户");
		return false;
	}
	if(rows[0].status != 2){
		tip.msg("只有未认证用户才能进行认证");
		return false;
	}
    var postData = {
        id: rows[0].id,
		status: 0
	};
	tip.confirm('您确定要认证该用户吗？', function(){
		load.post('/user/update', postData, true, function(data){
			if(data.status == 0){
				loadData();
				tip.msg(data.msg);
			}else {
				tip.msg(data.msg);
			}
		});
	});
}