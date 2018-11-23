<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>二手房管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/business/houseOldManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="二手房列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:true,pagination:true,pageSize:10,fitColumns:false,striped:true,nowrap:false,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="pictures" width="420" height="auto" align="center" formatter="FormatterPicture">房屋图片</th>
                <th field="title" width="150" align="center">房屋标题</th>
                <th field="countRoom" width="60" align="center">卧室数量</th>
                <th field="countToilet" width="65" align="center">卫生间数量</th>
                <th field="countHall" width="60" align="center">厅数量</th>
                <th field="decoration" width="80" align="center">装修情况</th>
                <th field="acreage" width="80" align="center" formatter="FormatterAcreage">房屋面积</th>
                <th field="price" width="80" align="center" formatter="FormatterPrice">房屋预计总价价</th>
                <th field="build" width="80" align="center">房子建造年份</th>
                <th field="address" width="100" align="center">房屋地址</th>
                <th field="linkphone" width="85" align="center">联系电话</th>
                <th field="linkman" width="80" align="center">联系人</th>
                <th field="linkqq" width="80" align="center">联系qq</th>
                <th field="status" width="80" align="center" formatter="FormatterStatus">状态</th>
                <th field="createTime" width="150" align="center">创建时间</th>
                <th field="remarks" width="500" align="center">房屋详情</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
	   		<span class="con-span">房屋内容: </span>
		   	<input type="text" class="input_2 txtinput1" id="txtTile" />
	   		<span class="con-span">房屋状态: </span>
		   	<select class="input_2 txtinput1" id="txtStatus">
		   		<option value="-1">请选择</option>
				<option value="0">正常</option>
				<option value="1">已过期</option>
			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
		        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editOldHouseStatus(0)">启用</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editOldHouseStatus(1)">下架</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="loadData()">刷新</a>
	    </div>
    </div>
</div>
</body> 
</html>