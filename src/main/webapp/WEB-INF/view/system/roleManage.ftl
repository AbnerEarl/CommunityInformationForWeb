<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>角色管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/system/roleManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="角色列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:true,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="id" width="50" align="center">角色ID</th>
                <th field="name" width="100" align="center">角色名称</th>
                <th field="tip" width="100" align="center">角色标识</th>
                <th field="remarks" width="300" align="center">角色描述</th>
                <th field="status" width="70" align="center" formatter="FormatterStatus">角色状态</th>
                <th field="createTime" width="150" align="center">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
	   		<span class="con-span">角色名称: </span>
	   		<input class="input_2 txtinput1" type="text" id="txtName" />
   			<span class="con-span">角色状态: </span>
   			<select class="input_2" id="txtStatus" >
   			    <option value="-1">请选择</option>
   			    <option value="0">可用</option>
   			    <option value="1">不可用</option>
   			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
	        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addRole()">新增</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editRole()">编辑</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="menuToRole()">权限分配</a>
	    </div>
    </div>
</div>
<div id="menu_tree" class="easyui-dialog" data-options="closed:true,modal:true" style="width: 270px; height: 400px;">
    <ul id="sysmenu" class="easyui-tree" data-options="animate:true,checkbox:true">
    </ul>
    <div id="tbs" style="padding:5px 20px;">
	    <div class="opt-buttons">
	        <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveMenuPermission()">保存</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancalMenuPermission()">取消</a>
	    </div>
    </div>
</div>
<div id="divAdd" class="easyui-dialog" title="新增角色" data-options="closed:true,modal:true" style="width:320px;height:280px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" border="0" width="300px" style="margin:10px auto 0px auto;">
			<tr>
				<td style="width:30%; text-align:right;">角色名称:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtAddName" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">角色标识:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtAddIdentifice" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">角色描述:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtAddRemarks" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">角色状态:</td>
				<td style="width:70%; text-align:left;">
				   	<select class="input_2 txtinput1" id="txtAddStatus">
	   			   		<option value="-1">请选择</option>
	   			  		<option value="0">可用</option>
	   			  		<option value="1">不可用</option>
	   				</select>
				</td>
			</tr>
			<tr>
	 			<td colspan="2" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="addRoleSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="addRoleCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="divEdit" class="easyui-dialog" title="编辑角色" data-options="closed:true,modal:true" style="width:320px;height:280px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" border="0" width="300px" style="margin:10px auto 0px auto;">
			<tr>
				<td style="width:30%; text-align:right;">角色名称:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtEditName" class="input_2 txtinput1" disabled="disabled" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">角色标识:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtEditIdentifice" class="input_2 txtinput1" disabled="disabled" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">角色描述:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtEditRemarks" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">角色状态:</td>
				<td style="width:70%; text-align:left;">
				   	<select class="input_2 txtinput1" id="txtEditStatus">
	   			  		<option value="0">可用</option>
	   			  		<option value="1">不可用</option>
	   				</select>
				</td>
			</tr>
			<tr>
	 			<td colspan="2" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="editRoleSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="editRoleCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
</body> 
</html>