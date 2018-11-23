$(function (){
	$(".picUpload").initOnePicUpload();
});

function loadData(){
	$('#dg').datagrid({  
   	    url: '/farmProduct/getFarmProductListByPage?status='+$("#txtStatus").val()+"&title=" + $("#txtTile").val()+"&farmId=" + $("#txtFarmId").val()
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
		return "<font color='red'>下架</font>";
	}
}

function FormatterPrice(value, rowData, rowIndex){
	return value + "元/斤";
}

function addFarmProduct(){
    $("#txtAddPictures").val("");
    $("#txtAddPictures").parent().find("img").remove();
    $("#txtAddTitle").val("");
    $("#txtAddPrice").val("");
	$("#divAdd").window("open");
}

function addFarmProductCancal(){
	$("#divAdd").window("close");
}

function addFarmProductSubmit(){
	if($("#txtAddPictures").val().length == 0){
		tip.msg("请上传图片");
		return false;
	}
	if($("#txtAddTitle").val().length == 0){
		tip.msg("请输入商品标题");
		return false;
	}
	if($("#txtAddPrice").val().length == 0){
		tip.msg("请输入商品单价");
		return false;
	}
    var postData = {
		pictures: $("#txtAddPictures").val(),
        title: $("#txtAddTitle").val(),
		price: $("#txtAddPrice").val(),
		farmId: $("#txtFarmId").val()
	};
	load.post("/farmProduct/add", postData, true, function(data){
        if(data.status == 0){
        	loadData();
        	addFarmProductCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function editFarmProduct(){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要编辑的数据");
        return false;
    }
    $("#txtEditTitle").val(rows[0].title);
    $("#txtEditPrice").val(rows[0].price);
	$("#divEdit").window("open");
}

function editFarmProductCancal(){
	$("#divEdit").window("close");
}

function editFarmProductSubmit(){
	if($("#txtEditTitle").val().length == 0){
		tip.msg("请输入农场名称");
		return false;
	}
	if($("#txtEditPrice").val().length == 0){
		tip.msg("请输入农场地址");
		return false;
	}
    var rows = $("#dg").datagrid('getChecked');
    var postData = {
    	id: rows[0].id,
        title: $("#txtEditTitle").val(),
		price: $("#txtEditPrice").val()
	};
	load.post("/farmProduct/update", postData, true, function(data){
        if(data.status == 0){
        	loadData();
        	editFarmProductCancal();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function editFarmStatus(status){
    var rows = $("#dg").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要操作的数据");
        return false;
    }
	tip.confirm("确认进行该操作", function(){
	    var postData = {
        	id: rows[0].id,
    		status: status
    	};
    	load.post("/farmProduct/update", postData, true, function(data){
            if(data.status == 0){
            	loadData();
            	tip.msg(data.msg);
            } else {
           		tip.msg(data.msg);
           	}
    	});
	})
}