<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>菜单管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link href="${request.contextPath}/static/content/css/common/menuManage.css" rel="stylesheet">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/system/menuManage.js"></script>
</head> 
<body class="layer-body">
<div class="container"> 
	<div class="left-tree">
		<ul id="sysmenu" class="easyui-tree" data-options="animate:true,onClick: function(node){menuToTable(node);}"></ul>
	</div>
	<div class="content">
		<table id="dg" class="easyui-datagrid" style="width:100%;height:554px" title="子级菜单列表" data-options="singleSelect:false,
   		autoRowHeight:false,loadMsg:0,fitColumns:true,striped:true,toolbar:'#tb'">
	        <thead>
	            <tr>
	            	<th field="ck" width="80" checkbox="true" ></th>
	                <th field="id" width="50" align="center">菜单ID</th>
	                <th field="name" width="120" align="center" editor="text">菜单名称</th>
	                <th field="parentId" width="60" align="center" hidden="true">父级菜单ID</th>
	                <th field="icon" width="80" align="center" formatter="FormatterIcon">小图标</th>
	                <th field="url" width="200" align="center" editor="text">链接</th>
	                <th field="sortId" width="50" align="center" editor="text">排序</th>
	                <th field="status" width="50" align="center" editor="{type: 'checkbox',options: {on: '1',off: '0'}}" formatter="FormatterStatus">是否可用</th>
	            </tr>
	        </thead>
    	</table>
	    <div id="tb" style="padding:0 20px;">
		    <div class="opt-buttons">
		        <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-add" onclick="add()">新增</a>
		        <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit()">编辑</a>
		        <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()">删除</a>
		        <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
		        <a href="javascript:;" class="easyui-linkbutton" iconCls="icon-undo" onclick="undo()">重置</a>
		    </div>
  		</div>
	</div>
</div>
<div id="icon_lists" class="easyui-dialog" title="选取图标" data-options="closed:true,modal:true" style="width:380px;height:240px;">
	<ul class="icon_lists"></ul>
</div>
</body> 
</html>
