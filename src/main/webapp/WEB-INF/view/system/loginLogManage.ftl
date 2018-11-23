<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>登录日志管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/system/loginLogManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="dg" class="easyui-datagrid" style="width:100%;height:100%" title="登陆日志列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:true,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
                <th field="logname" width="150" align="center">日志名称</th>
                <th field="ip" width="100" align="center">登录IP</th>
                <th field="createTime" width="150" align="center">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
    	<div class="conditions">
	   		<span class="con-span">日志名称: </span>
			<select id="txtLogname" class="input_2 txtinput1">
				<option value="">请选择</option>
				<option value="登录日志">登录日志</option>
				<option value="退出日志">退出日志</option>
			</select>
    		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadData()">查询</a>
    	</div>
    </div>
</div>
</body> 
</html>