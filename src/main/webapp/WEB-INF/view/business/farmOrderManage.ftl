<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>农场订单管理</title> 
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
<script type="text/javascript" src="${request.contextPath}/static/js/business/farmOrderManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="订单列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:false,nowrap:false,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="userName" width="150" align="center">下单人</th>
                <th field="userPhone" width="150" align="center">联系电话</th>
                <th field="userAddress" width="150" align="center">配送地址</th>
                <th field="statusValue" width="150" align="center">状态</th>
                <th field="createTime" width="150" align="center">下单时间</th>
                <th field="id" width="150" align="center" formatter="FormatterAction">操作</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
		   	<input type="hidden" id="txtFarmId" value="${farm.id}" />
   			<span class="con-span">订单状态: </span>
   			<select class="input_2" id="txtStatus" >
   			    <option value="-1">请选择</option>
   			    <option value="0">待发货</option>
   			    <option value="1">已发货</option>
   			    <option value="2">交易成功</option>
   			    <option value="3">交易失败</option>
   			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editOrderStatus(1)">一键发货</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editOrderStatus(2)">一键成功</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editOrderStatus(3)">一键失败</a>
	    </div>
    </div>
</div>
<div id="divProduct" class="easyui-dialog" title="订单产品列表" data-options="closed:true,modal:true" style="width:720px;height:400px;">
	<div class="easyui-layout" data-options="fit:true,region:'center'">
	    <table id="productList" class="easyui-datagrid" style="width:100%;height:100%" data-options="rownumbers:true,singleSelect:true,
	    autoRowHeight:true,pagination:true,pageSize:10,fitColumns:true,striped:true">
	        <thead>
	            <tr>
	            	<th field="ck" width="80" checkbox="true" ></th>
	                <th field="picture" width="150" height="auto" align="center" formatter="FormatterPicture">商品图片</th>
	                <th field="name" width="200" align="center">商品标题</th>
	                <th field="price" width="100" align="center" formatter="FormatterPrice">单价</th>
	                <th field="num" width="100" align="center">数量</th>
	                <th field="createTime" width="150" align="center">创建时间</th>
	            </tr>
	        </thead>
	    </table>
	</div>
</div>
</body> 
</html>