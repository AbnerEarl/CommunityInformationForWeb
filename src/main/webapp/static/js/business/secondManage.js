function loadData(){
	$('#dg').datagrid({  
   	    url: '/second/getSecondListByPage?category='+$("#txtCategory").val()+"&price="+$("#txtPrice").val()+"&status="+$("#txtStatus").val()
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
		return "<font color='red'>已过期</font>";
	}
}

function FormatterCategory(value, rowData, rowIndex) {
	if(value == 1){
		return "家具";
	}else if(value == 2){
		return "电器";
	}else if(value == 3){
		return "杂物";
	}else if(value == 4){
		return "其他";
	}
}

function FormatterDegree(value, rowData, rowIndex) {
	if(value == 1){
		return "全新";
	}else if(value == 2){
		return "6-9成新";
	}else if(value == 3){
		return "5成新";
	}else if(value == 4){
		return "5成新以下";
	}
}

function FormatterPrice(value, rowData, rowIndex) {
	if(value == 1){
		return "面议";
	}else if(value == 2){
		return "交换同价值物品";
	}else if(value == 3){
		return "0-200元";
	}else if(value == 4){
		return "200-500元";
	}else if(value == 5){
		return "500-1000元";
	}else if(value == 6){
		return "1000-5000元";
	}else if(value == 7){
		return "5000元以上";
	}
}

var secondId = null;
function lookSecondAsk(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要查看的一条数据");
        return false;
    }
    secondId = rows[0].id;
    loadSecondAsk();
	$("#divAsk").dialog('open');
}

function loadSecondAsk(){
	$('#askList').datagrid({  
   	    url: '/secondAsk/getSecondAskListByPage?secondId=' + secondId
	}); 
}

function deleteAsk(){
    var rows = $("#askList").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要删除的一条数据");
        return false;
    }
    tip.confirm("确认删除该回复？",function(){
    	load.get("/secondAsk/delete?id=" + rows[0].id, true, function(data){
    		if(data.status == 0){
    			loadSecondAsk();
    			tip.msg(data.msg);
    		} else {
    			tip.msg(data.msg);
    		}
    	});
    });
}

function addAsk(){
	$("#txtAskAddContent").val("");
	$("#divAddAsk").dialog('open');
}

function addAskCancle(){
	$("#divAddAsk").dialog('close');
}

function addAskSubmit(){
    if ($("#txtAskAddContent").val().length == 0) {
        tip.msg("请输入回复内容");
        return false;
    }
    var postData = {
		content: $("#txtAskAddContent").val(),
		secondId: secondId
	};
	load.post("/secondAsk/add", postData, true, function(data){
        if(data.status == 0){
        	loadSecondAsk();
        	addAskCancle();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function editSecondStatus(status){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要操作的数据");
        return false;
    }
    var msg = "";
    if(status == 0){
    	msg = "确认要启用这条数据？";
    }else {
    	msg = "确认要下架这条数据？";
    }
    tip.confirm(msg, function(){
    	var postData = {
			id: rows[0].id,
			status: status
    	};
    	load.post("/second/update", postData, true, function(data){
    		if(data.status == 0){
    			tip.msg(data.msg);
    			loadData();
    		} else {
    			tip.msg(data.msg);
    		}
    	});
    });
}