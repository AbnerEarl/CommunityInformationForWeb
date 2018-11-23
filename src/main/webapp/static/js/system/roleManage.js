var node = null;
$(function(){
	initMenuTree();
}); 

function initMenuTree(){
	if(node != null){
		$('#sysmenu').tree('remove', node.target);
	}
	var rootmenu = '{\"\id\"\:\"-1\",\"text\":\"功能菜单\",\"state\":\"closed\"}';
	rootmenu = eval("(" + rootmenu + ")");
    $('#sysmenu').tree('append', {
        parent: null,
        data: rootmenu
    });
	var node = $('#sysmenu').tree('getRoot');
	load.get('/menu/getChildMenuList?parentId=-1', false, function(data){
		var menus = JSON.parse(data);
		if(menus != '' && menus != null){
			$('#sysmenu').tree('append', {
				parent: (node ? node.target : null),
				data: menus
			});
		}
	});
}

function loadData(){
	$('#dg').datagrid({  
   	    url: '/role/getRoleListByPage?name='+$("#txtName").val()+'&status='+$("#txtStatus").val()
	}); 
}

function FormatterStatus(value, rowData, rowIndex) {
	if(value==0){
		return "可用";
	}else{
		return "<font color='red'>不可用</font>";
	}
}

function menuToRole(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要操作的一条数据");
        return false;
    }
    var id = rows[0].id;
    var name = rows[0].name;
    var node = $('#sysmenu').tree('getChecked');
    $(node).each(function () {
        $('#sysmenu').tree('uncheck', this.target);
    });
    var addMenuIds = [];
    $('#menu_tree').dialog({
        title: name + "权限设置",
        toolbar: '#tbs'
    });
	load.get('/menu/getRoleMenu?roleId=' + id, false, function(data){
        var rows = data;
        for (var i = 0; i < rows.length; i++) {
            var node = $('#sysmenu').tree('find', rows[i]["id"]);
            if (node != null) {
            	if($('#sysmenu').tree('getChildren', node.target).length == 0){
            		$('#sysmenu').tree('check', node.target);
            	}
            }else {
            	 addMenuIds.push(result[i]["id"]);
            }
        }
	});
    $('#menu_tree').window('open');
}

function cancalMenuPermission(){
	$('#menu_tree').window('close');
}

function saveMenuPermission(){
	var addMenuIds = [];
	var rows = $("#dg").datagrid('getChecked');
	var node = $('#sysmenu').tree('getChecked');
	var nodes_parent = $('#sysmenu').tree('getChecked', 'indeterminate');
	$(nodes_parent).each(function() {
		if ($(this).attr("id") != -1) {
			addMenuIds.push($(this).attr("id"));
		}
	});
	$(node).each(function() {
		if ($(this).attr("id") != -1) {
			addMenuIds.push($(this).attr("id"));
		}
	});
	updateMenuToRole(rows[0].id, addMenuIds);
	$('#menu_tree').window('close');
}

function updateMenuToRole(roleId, addMenuIds) {
	var add = "";
	add = addMenuIds.join(",");
	$.ajax({
		type : "GET",
		contentType : "application/json",
		dataType : "json",
		url : "/role/updateMenuToRole?roleId=" + roleId + "&addMenu=" + add,
		success : function(data) {
			tip.closeAll();
			if (data.status == 0) {
				tip.msg(data.msg);
			} else {
				tip.msg(data.msg);
			}
			addMenuIds.splice(0, addMenuIds.length);
		},
		beforeSend : function() {
			tip.loading();
		},
		error : function(xhr) {
			tip.closeAll();
			tip.msg("服务器请求异常");
		}
	});
}

function addRole(){
	$("#divAdd").dialog('open');
	$("#txtAddName").val('');
	$("#txtAddIdentifice").val('');
	$("#txtAddRemarks").val('');
	$("#txtAddStatus").val(-1);
}

function addRoleCancal(){
	$("#divAdd").dialog('close');
}

function addRoleSubmit(){
	if($("#txtAddName").val().length == 0){
     	tip.tips("角色名称不能为空", "#txtAddName");
		return false;
	}
	if($("#txtAddIdentifice").val().length == 0){
		tip.tips("角色标识不能为空", "#txtAddName");
		return false;
	}
	if($("#txtAddStatus").val() == -1){
		tip.tips("请选择角色状态", "#txtAddStatus");
		return false;
	}
    var postData = {
        name: $("#txtAddName").val(),
        tip: $("#txtAddIdentifice").val(),
		remarks: $("#txtAddRemarks").val(),
		status: $("#txtAddStatus").val()
	};
	load.postJson("/role/add", JSON.stringify(postData), true, function(data){
        if(data.status == 0){
        	loadData();
        	addRoleCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function editRole(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要编辑的数据");
        return false;
    }
	$("#txtEditName").val(rows[0].name);
	$("#txtEditIdentifice").val(rows[0].tip);
	$("#txtEditRemarks").val(rows[0].remarks);
	$("#txtEditStatus").val(rows[0].status);
	$("#divEdit").dialog('open');
}

function editRoleCancal(){
	$("#divEdit").dialog('close');
}

function editRoleSubmit(){
	var rows = $("#dg").datagrid('getChecked');
    var postData = {
        id: rows[0].id,
		remarks: $("#txtEditRemarks").val(),
		status: $("#txtEditStatus").val()
	};
	load.postJson("/role/update", JSON.stringify(postData), true, function(data){
        if(data.status == 0){
        	loadData();
        	editRoleCancal();
        	tip.msg(data.msg);
        }else {
       		tip.msg(data.msg);
       	}
	});
}