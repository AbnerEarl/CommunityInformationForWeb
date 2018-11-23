function loadData(){
	$('#dg').datagrid({  
   	    url: '/farmOrder/getFarmOrderListByPage?status='+$("#txtStatus").val() + "&farmId=" + $("#txtFarmId").val()
	}); 
}

function FormatterAction(value, rowData, rowIndex){
	return "<a href='javascript:LookOrder(" + '\"' + rowData.id + '\"' + ")' >查看订单详情</a>";
}

function editOrderStatus(status){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length == 0) {
        tip.msg("请选择要操作的数据");
        return false;
    }
    var array = new Array();
    $.each(rows, function(index,row){
    	var postData = {
			id: row.id,
			status: status
    	};
    	array.push(postData);
    })
	tip.confirm("确认进行该操作", function(){
    	load.postJson("/farmOrder/update", JSON.stringify(array), true, function(data){
            if(data.status == 0){
            	loadData();
            	tip.msg(data.msg);
            } else {
           		tip.msg(data.msg);
           	}
    	});
	})
}

function LookOrder(id){
	$("#divProduct").window("open");
	$('#productList').datagrid({  
   	    url: '/orderRecord/getOrderRecordByPage?farmOrderId='+ id
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

function FormatterPrice(value, rowData, rowIndex){
	return value + "元/斤";
}