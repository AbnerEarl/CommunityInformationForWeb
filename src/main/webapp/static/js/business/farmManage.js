$(function (){
	$(".picUpload").initPicUpload();
});

function loadData(){
	$('#dg').datagrid({  
   	    url: '/farm/getFarmListByPage?status='+$("#txtStatus").val()
	}); 
}

function FormatterPicture(value, rowData, rowIndex) {
	var pics = value.split(",");
	if(pics){
		var html = "";
		$.each(pics, function(index, pic){
			html = html + "<a href='"+pic+"' target=_blank><img src='" + pic + "' style='width:80px;height:80px;border:1px solid #e8e8e8;margin:10px' /></a>";
		})
		return html;
	}
}

function FormatterStatus(value, rowData, rowIndex) {
	if(value == 0){
		return "正常";
	}else if(value == 1){
		return "<font color='red'>冻结</font>";
	}
}

function addFarm(){
    $("#txtAddUserName").html("");
    $("#txtAddUser").val("");
    $("#txtAddPictures").val("");
    $("#txtAddPictures").siblings("div.pics").find("ul").html("");
    $("#txtAddName").val("");
    $("#txtAddAddress").val("");
    $("#txtAddLinkman").val("");
    $("#txtAddLinkphone").val("");
    $("#txtAddRemarks").val("");
	$("#divAdd").window("open");
}

function addFarmCancal(){
	$("#divAdd").window("close");
}

function addFarmSubmit(){
	if($("#txtAddUser").val().length == 0){
     	tip.msg("请选择注册人");
		return false;
	}
	if($("#txtAddPictures").val().length == 0){
		tip.msg("请上传图片");
		return false;
	}
	if($("#txtAddName").val().length == 0){
		tip.msg("请输入农场名称");
		return false;
	}
	if($("#txtAddAddress").val().length == 0){
		tip.msg("请输入农场地址");
		return false;
	}
	if($("#txtAddLinkman").val().length == 0){
		tip.msg("请输入联系人");
		return false;
	}
	if($("#txtAddLinkphone").val().length == 0){
		tip.msg("请输入联系电话");
		return false;
	}
	if($("#txtAddRemarks").val().length == 0){
		tip.msg("请输入农场详情");
		return false;
	}
    var postData = {
		userId: $("#txtAddUser").val(),
		pictures: $("#txtAddPictures").val(),
        name: $("#txtAddName").val(),
		address: $("#txtAddAddress").val(),
		linkman: $("#txtAddLinkman").val(),
		linkphone: $("#txtAddLinkphone").val(),
		remarks: $("#txtAddRemarks").val()
	};
	load.post("/farm/add", postData, true, function(data){
        if(data.status == 0){
        	loadData();
        	addFarmCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function selectUser(){
	$("#divUser").window("open");
	loadUser() 
}

function selectUserSubmit(){
    var rows = $("#userList").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要注册农场的用户");
        return false;
    }
    $("#txtAddUserName").html(rows[0].name);
    $("#txtAddUser").val(rows[0].id);
	$("#divUser").window("close");
}

function loadUser(){
	$('#userList').datagrid({  
   	    url: '/user/list?roleId=4' 
	}); 
}

function FormatterSex(value, rowData, rowIndex) {
	if(value == 1){
		return "男";
	}else if(value == 2){
		return "女";
	}
}

function editFarm(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要编辑的数据");
        return false;
    }
    $("#txtEditName").val(rows[0].name);
    $("#txtEditAddress").val(rows[0].address);
    $("#txtEditLinkman").val(rows[0].linkman);
    $("#txtEditLinkphone").val(rows[0].linkphone);
    $("#txtEditRemarks").val(rows[0].remarks);
	$("#divEdit").window("open");
}

function editFarmCancal(){
	$("#divEdit").window("close");
}

function editFarmSubmit(){
	if($("#txtEditName").val().length == 0){
		tip.msg("请输入农场名称");
		return false;
	}
	if($("#txtEditAddress").val().length == 0){
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
    var rows = $("#dg").datagrid('getChecked');
    var postData = {
    	id: rows[0].id,
        name: $("#txtEditName").val(),
		address: $("#txtEditAddress").val(),
		linkman: $("#txtEditLinkman").val(),
		linkphone: $("#txtEditLinkphone").val(),
		remarks: $("#txtEditRemarks").val()
	};
	load.post("/farm/update", postData, true, function(data){
        if(data.status == 0){
        	loadData();
        	editFarmCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}