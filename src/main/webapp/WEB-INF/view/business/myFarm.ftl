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
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/script/other/fillOptions.js"></script> 
</head> 
<body class="layer-body">
  <div class="basic-info">
  	<table class="kv-table">
		<tbody>
			<tr>
				<td class="kv-label">农场名称</td>
				<td class="kv-content">${farm.name}</td>
				<td class="kv-label">联系人</td>
				<td class="kv-content">${farm.linkman}</td>
				<td class="kv-label">联系电话</td>
				<td class="kv-content">${farm.linkphone}</td>
			</tr>
			<tr>
				<td class="kv-label">农场地址</td>
				<td class="kv-content" colspan="5">${farm.address}</td>
			</tr>
			<tr>
				<td class="kv-label">农场详情</td>
				<td class="kv-content" colspan="5">${farm.remarks}</td>
			</tr>
			<tr>
				<td class="kv-label">农场图片</td>
				<td class="kv-content" colspan="5">
					<#list farm.pictureList as pic>
						<img src="${pic}" width="180" height="120" />
					</#list>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body> 
</html>