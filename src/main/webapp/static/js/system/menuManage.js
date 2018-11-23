var delmenulist = new Array();
var parentId = 0;
var node = null;
$(function (){
    initMenuTree();
    selectIcon();
    $('#dg').datagrid({
    	url: '/menu/getChildMenuListForTable?parentId=0'
    });
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
	node = $('#sysmenu').tree('getRoot');
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

function menuToTable(node){
	var id = $(node).attr('id');
    if (typeof (id) != "undefined") {
    	if(id == -1){
    		id = 0;
    	}
    	parentId = id;
        $('#dg').datagrid({
   	    	url: '/menu/getChildMenuListForTable?parentId='+id+'&viewtype=table'
        });
    }
}

function FormatterIcon(value, row, index){
	if(value != null && value != ""){
		return "<a href='javascript:;' style='text-decoration:none;' onclick='InconSelector("+ '\"' + index + '\"' +")'><span class='iconfont sider-nav-icon'>"+ value +"</span></a>";
	}else {
		return "<a href='javascript:;' style='text-decoration:none;' onclick='InconSelector("+ '\"' + index + '\"' +")'>选择</a>";
	}
}

function FormatterStatus(value, row, index){
	if(value == 0){
		return "可用";
	}else if(value == 1){
		return "<font color='red'>不可用</font>";
	}else {
		return "<font color='blue'>未知</font>";
	}
}

function add(){
	var lastIndex = $('#dg').datagrid('getRows').length;
	$('#dg').datagrid('appendRow', {
		id: "0",
		name: "",
		parentId: parentId,
		icon: "",
		url: "",
		sortId: "",
		status: "0"
	});
	$('#dg').datagrid('selectRow', lastIndex);
	$('#dg').datagrid('beginEdit', lastIndex);
}

function InconSelector(index){
	var iconlist = ["&#xe6b7;","&#xe684;","&#xe608;","&#xe69a;","&#xe691;","&#xe6bc;","&#xe6bd;","&#xe69b;","&#xe660;","&#xe630;","&#xe68c;","&#xe61d;","&#xe6fe;","&#xe69c;","&#xe61c;","&#xe60a;","&#xe6b3;","&#xe6b4;","&#xe6b5;","&#xe6b6;","&#xe670;","&#xe633;","&#xe6e1;","&#xe6ea;","&#xe6e2;","&#xe6bf;","&#xe6c0;","&#xe6c1;","&#xe656;","&#xe640;","&#xe6aa;","&#xe69d;","&#xe636;","&#xe613;","&#xe6d0;","&#xe61e;","&#xe644;","&#xe641;","&#xe61f;","&#xe6a5;","&#xe657;","&#xe668;","&#xe661;","&#xe60d;","&#xe6c2;","&#xe60e;","&#xe6e9;","&#xe631;","&#xe642;","&#xe6d1;","&#xe635;","&#xe627;","&#xe628;","&#xe65b;","&#xe685;","&#xe6f6;","&#xe643;","&#xe6f7;","&#xe662;","&#xe663;","&#xe63b;","&#xe646;","&#xe6a6;","&#xe69f;","&#xe664;","&#xe6c6;","&#xe6ab;","&#xe629;","&#xe62a;","&#xe62b;","&#xe6ed;","&#xe6f8;","&#xe658;","&#xe62c;","&#xe637;","&#xe6d9;","&#xe605;","&#xe606;","&#xe634;","&#xe66e;","&#xe623;","&#xe686;","&#xe687;","&#xe647;","&#xe616;","&#xe648;","&#xe671;","&#xe6a7;","&#xe649;","&#xe692;","&#xe680;","&#xe688;","&#xe65c;","&#xe610;","&#xe6e0;","&#xe6ee;","&#xe6ca;","&#xe624;","&#xe620;","&#xe6fb;","&#xe645;","&#xe603;","&#xe64a;","&#xe6c4;","&#xe696;","&#xe64b;","&#xe6c7;","&#xe6e7;","&#xe6c5;","&#xe65d;","&#xe62d;","&#xe6fa;","&#xe6ac;","&#xe6e5;","&#xe659;","&#xe60f;","&#xe689;","&#xe6ef;","&#xe62e;","&#xe619;","&#xe604;","&#xe6cb;","&#xe672;","&#xe673;","&#xe665;","&#xe621;","&#xe6b8;","&#xe6ad;","&#xe600;","&#xe62f;","&#xe6fd;","&#xe690;","&#xe625;","&#xe66b;","&#xe63c;","&#xe61a;","&#xe61b;","&#xe669;","&#xe66a;","&#xe6c8;","&#xe60b;","&#xe6da;","&#xe601;","&#xe638;","&#xe6cf;","&#xe6db;","&#xe6d7;","&#xe639;","&#xe6ae;","&#xe622;","&#xe611;","&#xe65e;","&#xe6be;","&#xe6de;","&#xe674;","&#xe63d;","&#xe614;","&#xe6a8;","&#xe63e;","&#xe6df;","&#xe650;","&#xe612;","&#xe6f9;","&#xe6d2;","&#xe64c;","&#xe60c;","&#xe666;","&#xe675;","&#xe676;","&#xe677;","&#xe678;","&#xe679;","&#xe67a;","&#xe67b;","&#xe67c;","&#xe6af;","&#xe6f3;","&#xe64f;","&#xe698;","&#xe632;","&#xe6f4;","&#xe681;","&#xe615;","&#xe699;","&#xe6cc;","&#xe6fc;","&#xe66c;","&#xe65f;","&#xe6a9;","&#xe64e;","&#xe6a0;","&#xe652;","&#xe6e8;","&#xe6e3;","&#xe6f0;","&#xe6eb;","&#xe602;","&#xe607;","&#xe609;","&#xe63a;","&#xe65a;","&#xe617;","&#xe618;","&#xe653;","&#xe626;","&#xe6f5;","&#xe67d;","&#xe67e;","&#xe64d;","&#xe69e;","&#xe6cd;","&#xe6ec;","&#xe693;","&#xe66f;","&#xe6dd;","&#xe6ce;","&#xe682;","&#xe63f;","&#xe67f;","&#xe694;","&#xe695;","&#xe6a2;","&#xe6f1;","&#xe6a3;","&#xe6f2;","&#xe6dc;","&#xe651;","&#xe6d3;","&#xe697;","&#xe6e4;","&#xe6b0;","&#xe66d;","&#xe6b9;","&#xe6b1;","&#xe6b2;","&#xe683;","&#xe6d4;","&#xe6a4;","&#xe6d5;","&#xe654;","&#xe6a1;","&#xe6ba;","&#xe68a;","&#xe68b;","&#xe6bb;","&#xe6ff;","&#xe667;","&#xe6e6;","&#xe655;","&#xe68d;","&#xe68e;","&#xe68f;","&#xe6d6;","&#xe6c9;","&#xe6d8;","&#xe6c3;"]
	$.each(iconlist, function(i, item){
		$(".icon_lists").append("<li data-value=" + item.replace("&","&amp;") + "><i class='icon iconfont'>" + item + "</i></li>");
	});
	$(".icon_lists").data("index", index);
	$("#icon_lists").dialog("open"); 
}

function selectIcon(){
	$(document).on('click', '.icon_lists li', function() {
		$("#icon_lists").dialog("close");
		var rows = $('#dg').datagrid("getRows");
		$('#dg').datagrid('endEdit', $(this).parent().data("index"));
		var row = rows[$(this).parent().data("index")];
   		$("#dg").datagrid("updateRow",{  
            index: $(this).parent().data("index"), 
            row: {  
    			id: row.id,
    			name: row.name,
    			parentId: row.parentId,
                icon: $(this).data("value"),
    			url: row.url,
    			sortId: row.sortId,
    			status: row.status
            }  
        });
   		$('#dg').datagrid('beginEdit', $(this).parent().data("index"));
	});
}

function edit(){
	var rows = $('#dg').datagrid('getChecked');
    if (rows.length > 0) {
    	$.each(rows, function(i, row){
    		var index = $('#dg').datagrid('getRowIndex', row);
    		$('#dg').datagrid('beginEdit', index);
    	});
    }else {
    	tip.msg("请选择要编辑的数据");
    }
}

function remove(){
    var rows = $('#dg').datagrid('getChecked');
    if (rows.length > 0) {
	    $.each(rows, function(i, row){
       		var index = $('#dg').datagrid('getRowIndex', row);
       		$('#dg').datagrid('deleteRow', index);
			 if (row.id != "") {
				 delmenulist.push(row.id);
			 }
    	});
    }else {
    	tip.msg("请选择要删除的数据");
    }	
}

function undo(){
	$('#dg').datagrid('rejectChanges');
	delmenulist = new Array();
}

function save(){
	BtnChanges();
	delmenulist = new Array();
}

function BtnChanges(){
	$('#dg').datagrid('acceptChanges');
	var datalist = getJsonMenuData();
    if (datalist.length > 0) {
    	load.postJson('/menu/updateMenu', JSON.stringify(datalist), true, function(data){
        	if(data.status == 0){
        		$('#dg').datagrid("reload");
        		initMenuTree();
        		tip.msg(data.msg);
        	} else{
        		tip.msg(data.msg);
        	}
    	});
    }
}

function getJsonMenuData() {
	var rows = $("#dg").datagrid("getRows");
	var datalist = new Array();
	for (var i = 0; i < rows.length; i++) {
	    var data = {};
	    var type = "add";
        if (typeof rows[i].id != "undefined" && rows[i].id != "0") {
        	type = "update";
        }
        if (typeof rows[i].name != "undefined" && rows[i].name != "") {
        	data.type = type;
        	data.id = rows[i].id;
        	data.name = rows[i].name;
        	data.parentId = rows[i].parentId;
        	data.icon = rows[i].icon;
        	data.url = rows[i].url;
        	data.sortId = rows[i].sortId;
        	data.status = rows[i].status;
        	datalist.push(data);
        }
	}
    for (var i = 0; i < delmenulist.length; i++) {
    	var data = {};
    	data.type = "del";
    	data.id = delmenulist[i];
    	datalist.push(data);
    }
    return datalist;
}