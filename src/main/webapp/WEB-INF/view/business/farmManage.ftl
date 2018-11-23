<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>农场管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<link href="${request.contextPath}/static/script/util/kindeditor/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/script/other/fillOptions.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/script/other/kindeditor.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/business/farmManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="农场列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:false,nowrap:false,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="pictures" width="420" height="auto" align="center" formatter="FormatterPicture">农场图片</th>
                <th field="name" width="100" align="center">农场名称</th>
                <th field="address" width="150" align="center">农场地址</th>
                <th field="remarks" width="200" align="center">农场详情</th>
                <th field="linkphone" width="120" align="center">联系电话</th>
                <th field="linkman" width="120" align="center">联系人</th>
                <th field="userName" width="100" align="center">注册人</th>
                <th field="status" width="70" align="center" formatter="FormatterStatus">状态</th>
                <th field="createTime" width="150" align="center">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
   			<span class="con-span">农场状态: </span>
   			<select class="input_2" id="txtStatus" >
   			    <option value="-1">请选择</option>
   			    <option value="1">正常</option>
   			    <option value="2">冻结</option>
   			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
	        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addFarm()">农场注册</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editFarm()">编辑</a>
	    </div>
    </div>
</div>
<div id="divAdd" class="easyui-dialog" title="农场注册" data-options="closed:true,modal:true" style="width:620px;height:400px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" border="0" width="600px" style="margin:10px auto 0px auto;">
			<tr>
				<td style="width:15%; text-align:right;">注册人:</td>
				<td style="width:35%; text-align:left;" colspan="3">
					<input type="hidden" id="txtAddUser" />
					<font color="red" id="txtAddUserName"></font>
	            	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectUser()">用户选择</a>
				</td>
			</tr>
			<tr align="center">
				<th style="width:15%; text-align:right;">农场图片：</th>
				<td style="width:85%; text-align:left;" colspan="3">     
					<input type="hidden" id="txtAddPictures" />
	            	<a href="javascript:void(0)" class="easyui-linkbutton picUpload">图片上传</a>
            	</td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">农场名称:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddName" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">农场地址:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddAddress" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">联系人:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddLinkman" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">联系电话:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtAddLinkphone" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">农场详情:</td>
				<td style="width:85%; text-align:left;" colspan="3"><textarea rows="3" cols="50" id="txtAddRemarks"></textarea></td>
			</tr>
			<tr>
	 			<td colspan="4" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="addFarmSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="addFarmCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="divEdit" class="easyui-dialog" title="编辑" data-options="closed:true,modal:true" style="width:620px;height:350px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpEditing="0" cellspacing="0" border="0" width="600px" style="margin:10px auto 0px auto;">
			<tr>
				<td style="width:15%; text-align:right;">农场名称:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditName" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">农场地址:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditAddress" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">联系人:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditLinkman" class="input_2 txtinput1" /></td>
				<td style="width:15%; text-align:right;">联系电话:</td>
				<td style="width:35%; text-align:left;"><input type="text" id="txtEditLinkphone" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">联系人:</td>
				<td style="width:35%; text-align:left;" colspan="3">
					<select id="txtEditStatus" class="input_2 txtinput1">
						<option value="-1">请选择</option>
						<option value="0">正常</option>
						<option value="1">冻结</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width:15%; text-align:right;">农场详情:</td>
				<td style="width:85%; text-align:left;" colspan="3"><textarea rows="3" cols="50" id="txtEditRemarks"></textarea></td>
			</tr>
			<tr>
	 			<td colspan="4" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="editFarmSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="editFarmCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="divUser" class="easyui-dialog" title="用户选择列表" data-options="closed:true,modal:true" style="width:720px;height:400px;">
	<div class="easyui-layout" data-options="fit:true,region:'center'">
	    <table id="userList" class="easyui-datagrid" style="width:100%;height:100%" data-options="rownumbers:true,singleSelect:true,
	    autoRowHeight:true,pagination:true,pageSize:10,fitColumns:true,striped:true,toolbar:'#tbUser'">
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
	            </tr>
	        </thead>
	    </table>
        <div id="tbUser" style="padding:0 20px;">
		    <div class="opt-buttons">
		        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="selectUserSubmit()">选择</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="loadUser()">刷新</a>
		    </div>
	    </div>
	</div>
</div>
</body> 
</html>