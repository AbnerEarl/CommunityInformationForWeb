<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>二手市场管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/business/secondManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="二手商品列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:true,pagination:true,pageSize:10,fitColumns:false,nowrap:false,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="pictures" width="420" height="auto" align="center" formatter="FormatterPicture">商品图片</th>
                <th field="category" width="100" align="center" formatter="FormatterCategory">商品类别</th>
                <th field="degree" width="100" align="center" formatter="FormatterDegree">新旧程度</th>
                <th field="price" width="100" align="center" formatter="FormatterPrice">商品价值</th>
                <th field="linkphone" width="120" align="center">联系电话</th>
                <th field="linkman" width="70" align="center">联系人</th>
                <th field="linkqq" width="80" align="center">联系qq</th>
                <th field="status" width="80" align="center" formatter="FormatterStatus">状态</th>
                <th field="userName" width="80" align="center">发起人</th>
                <th field="dealTime" width="150" align="center">创建时间</th>
                <th field="content" width="500" align="center">商品描述</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
	   		<span class="con-span">商品类别: </span>
		   	<select class="input_2 txtinput1" id="txtCategory">
		   		<option value="-1">请选择</option>
		  		<option value="1">家具</option>
		  		<option value="2">电器</option>
		  		<option value="3">杂物</option>
		  		<option value="4">其他</option>
			</select>
	   		<span class="con-span">商品价值: </span>
		   	<select class="input_2 txtinput1" id="txtPrice">
		   		<option value="-1">请选择</option>
				<option value="1">面议</option>
				<option value="2">交换同价值物品</option>
				<option value="3">0-200元</option>
				<option value="4">200-500元</option>
				<option value="5">500-1000元</option>
				<option value="6">1000-5000元</option>
				<option value="7">5000元以上</option>
			</select>
	   		<span class="con-span">商品状态: </span>
		   	<select class="input_2 txtinput1" id="txtStatus">
		   		<option value="-1">请选择</option>
				<option value="0">正常</option>
				<option value="1">已过期</option>
			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
	    <div class="opt-buttons">
		        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editSecondStatus(0)">启用</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editSecondStatus(1)">下架</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="lookSecondAsk()">查看回复记录</a>
		        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="loadData()">刷新</a>
	    </div>
    </div>
</div>
<div id="divAsk" class="easyui-dialog" title="查看回复记录" data-options="closed:true,modal:true" style="width:720px;height:400px;">
	<div class="easyui-layout" data-options="fit:true,region:'center'">
	    <table id="askList" class="easyui-datagrid" style="width:100%;height:100%" data-options="rownumbers:true,singleSelect:false,
	    autoRowHeight:true,pagination:true,pageSize:10,fitColumns:false,striped:true,toolbar:'#tbAsk'">
	        <thead>
	            <tr>
	            	<th field="ck" width="80" checkbox="true" ></th>
	                <th field="userName" width="120" align="center">回复人</th>
	                <th field="content" width="300" align="center">回复内容</th>
	                <th field="dealTime" width="150" align="center">回复时间</th>
	            </tr>
	        </thead>
	    </table>
        <div id="tbAsk" style="padding:0 20px;">
		    <div class="opt-buttons">
			        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addAsk()">回复</a>
			        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteAsk()">删除</a>
			        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="loadSecondAsk()">刷新</a>
		    </div>
    	</div>
	</div>
</div>
<div id="divAddAsk" class="easyui-dialog" title="新增回复" data-options="closed:true,modal:true" style="width:420px;height:300px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" border="0" width="400px" style="margin:10px auto 0px auto;">
			<tr align="center">
				<th style="width:30%; text-align:right;">回复内容：</th>
				<td style="width:70%; text-align:left;">     
	            	<textarea style="width:100%;height:150px;" id="txtAskAddContent"></textarea>
            	</td>
			</tr>
			<tr>
	 			<td colspan="2" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="addAskSubmit();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="addAskCancle();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
</body> 
</html>