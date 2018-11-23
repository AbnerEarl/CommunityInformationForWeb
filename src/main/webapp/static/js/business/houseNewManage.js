function loadData(){
	$('#dg').datagrid({  
   	    url: '/houseNew/getHouseNewListByPage?title='+$("#txtTile").val()+"&status="+$("#txtStatus").val()
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

function FormatterAcreage(value, rowData, rowIndex) {
	return value + "m²";
}

function FormatterPrice(value, rowData, rowIndex) {
	return value + "元/平";
}

function editNewHouseStatus(status){
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
    	load.post("/houseNew/update", postData, true, function(data){
    		if(data.status == 0){
    			tip.msg(data.msg);
    			loadData();
    		} else {
    			tip.msg(data.msg);
    		}
    	});
    });
}