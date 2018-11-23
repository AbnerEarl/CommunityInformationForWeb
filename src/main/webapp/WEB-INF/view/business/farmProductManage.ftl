<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>农场商品管理</title> 
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
<script type="text/javascript" src="${request.contextPath}/static/js/business/farmProductManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="商品列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:false,nowrap:false,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="pictures" width="150" height="auto" align="center" formatter="FormatterPicture">商品图片</th>
                <th field="title" width="250" align="center">商品标题</th>
                <th field="price" width="150" align="center" formatter="FormatterPrice">单价</th>
                <th field="status" width="150" align="center" formatter="FormatterStatus">状态</th>
                <th field="createTime" width="150" align="center">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
	   		<span class="con-span">商品标题: </span>
		   	<input type="hidden" id="txtFarmId" value="${farm.id}" />
		   	<input type="text" class="input_2 txtinput1" id="txtTile" />
   			<span class="con-span">商品状态: </span>
   			<select class="input_2" id="txtStatus" >
   			    <option value="-1">请选择</option>
   			    <option value="1">正常</option>
   			    <option value="2">冻结</option>
   			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
	        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addFarmProduct()">新增</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editFarmProduct()">编辑</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editFarmStatus(0)">上架</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editFarmStatus(1)">下架</a>
	    </div>
    </div>
</div>
<div id="divAdd" class="easyui-dialog" title="新增" data-options="closed:true,modal:true" style="width:420px;height:250px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" border="0" width="400px" style="margin:10px auto 0px auto;">
			<tr align="center">
				<th style="width:15%; text-align:right;">商品图片：</th>
				<td style="width:85%; text-align:left;" colspan="3">     
					<input type="hidden" id="txtAddPictures" />
	            	<a href="javascript:void(0)" class="easyui-linkbutton picUpload">图片上传</a>
            	</td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">商品标题:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtAddTitle" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">商品单价:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtAddPrice" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
	 			<td colspan="4" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="addFarmProductSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="addFarmProductCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="divEdit" class="easyui-dialog" title="编辑" data-options="closed:true,modal:true" style="width:420px;height:250px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpEditing="0" cellspacing="0" border="0" width="400px" style="margin:10px auto 0px auto;">
			<tr>
				<td style="width:30%; text-align:right;">商品标题:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtEditTitle" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">商品单价:</td>
				<td style="width:70%; text-align:left;"><input type="text" id="txtEditPrice" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
	 			<td colspan="4" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="editFarmProductSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="editFarmProductCancal();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
</body> 
</html>