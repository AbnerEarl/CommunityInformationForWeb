<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>用户管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/script/other/fillOptions.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/script/other/ajaxFileUpload.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/system/userManage.js"></script>
</head>   
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="用户列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:false,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="username" width="100" align="center">用户名</th>
                <th field="name" width="80" align="center">姓名</th>
                <th field="idcard" width="150" align="center">身份证</th>
                <th field="sex" width="50" align="center" formatter="FormatterSex">性别</th>
                <th field="address" width="200" align="center">地址</th>
                <th field="phone" width="120" align="center">联系电话</th>
                <th field="email" width="150" align="center">邮箱</th>
                <th field="roleName" width="80" align="center">角色名称</th>
                <th field="status" width="70" align="center" formatter="FormatterStatus">用户状态</th>
                <th field="createTime" width="150" align="center">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
	   		<span class="con-span">条件: </span>
	   		<input class="input_2 txtinput1" type="text" id="txtCondition" placeholder="用户名/姓名/联系电话" />
   			<span class="con-span">角色名称: </span>
   			<select class="input_2" id="txtRole" ></select>
   			<span class="con-span">用户状态: </span>
		   	<select class="input_2 txtinput1" id="txtStatus">
		   		<option value="-1">请选择</option>
		  		<option value="0">可用</option>
		  		<option value="1">不可用</option>
		  		<option value="2">未认证</option>
			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
	    	<#if roleId == 1>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addUser()">新增</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editUser()">编辑</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="resetPass()">重置密码</a>
	    	</#if>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="confirmUser()">认证确认</a>
	    </div>
    </div>
</div>
<div id="divAdd" class="easyui-dialog" title="新增用户" data-options="closed:true,modal:true" style="width:600px;height:320px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" width="580px" style="margin:10px auto 0px auto;">
			<tr align="center">
				<td style="width:15%; text-align:right;">用户名:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddUsername" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">姓名:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddName" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<td style="width:15%; text-align:right;">性别:</td>
				<td style="width:35%; text-align:left;">
				   	<select class="input_2 txtinput1" id="txtAddSex">
	   			   		<option value="-1">请选择</option>
	   			  		<option value="1">男</option>
	   			  		<option value="2">女</option>
	   				</select>
				</td>
				<td style="width:15%; text-align:right;">地址:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddAddress" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<td style="width:15%; text-align:right;">邮箱:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddEmail" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">联系电话</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddPhone" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<td style="width:15%; text-align:right;">身份证号码:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddIdcard" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">角色名称:</td>
				<td style="width:35%; text-align:left;"><select class="input_2 txtinput1" id="txtAddRole" ></select></td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">用户状态:</td>
				<td style="width:35%; text-align:left;">
				   	<select class="input_2 txtinput1" id="txtAddStatus">
	   			   		<option value="-1">请选择</option>
	   			  		<option value="0">可用</option>
	   			  		<option value="1">不可用</option>
	   				</select>
				</td>
			</tr>
			<tr align="center">
	 			<td colspan="4" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="addUserSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="addUserCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="divEdit" class="easyui-dialog" title="编辑用户" data-options="closed:true,modal:true" style="width:600px;height:320px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpEditing="0" cellspacing="0" width="580px" style="margin:10px auto 0px auto;">
			<tr align="center">
				<td style="width:15%; text-align:right;">用户名:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditUsername" class="input_2 txtinput1" disabled="disabled" /></td>
				<td style="width:15%; text-align:right;">姓名:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditName" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<td style="width:15%; text-align:right;">性别:</td>
				<td style="width:35%; text-align:left;">
				   	<select class="input_2 txtinput1" id="txtEditSex">
	   			  		<option value="1">男</option>
	   			  		<option value="2">女</option>
	   				</select>
				</td>
				<td style="width:15%; text-align:right;">地址:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditAddress" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<td style="width:15%; text-align:right;">邮箱:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditEmail" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">联系电话</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditPhone" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<td style="width:15%; text-align:right;">身份证号码:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditIdcard" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">角色名称:</td>
				<td style="width:35%; text-align:left;"><select class="input_2 txtinput1" id="txtEditRole" ></select></td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">用户状态:</td>
				<td style="width:35%; text-align:left;">
				   	<select class="input_2 txtinput1" id="txtEditStatus">
	   			  		<option value="0">可用</option>
	   			  		<option value="1">不可用</option>
	   				</select>
				</td>
			</tr>
			<tr align="center">
	 			<td colspan="4" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="editUserSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="editUserCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
</body> 
</html>
