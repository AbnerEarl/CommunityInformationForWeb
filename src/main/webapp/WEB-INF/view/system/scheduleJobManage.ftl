<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8">  
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>定时器任务管理</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link rel="stylesheet" href="${request.contextPath}/static/content/css/common/manage.css">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/js/system/scheduleJobManage.js"></script>
</head> 
<body class="layer-body">
<div class="container easyui-layout" data-options="fit:true">
    <table id="quartzList" class="easyui-datagrid" style="width:100%;height:100%" title="定时器任务列表" data-options="rownumbers:true,singleSelect:false,
    autoRowHeight:false,pagination:true,pageSize:10,fitColumns:true,striped:true,toolbar:'#tb'">
        <thead>
            <tr>
            	<th field="ck" width="80" checkbox="true" ></th>
				<th field="jobName" width="80" align="center" align="center">Job名称</th>
				<th field="jobGroup" width="80" align="center" align="center">Job分组</th>
				<th field="jobStatus" width="60" align="center" align="center" formatter="FormatterJobStatus">Job状态</th>
				<th field="cronExpression" width="120" align="center" align="center">Cron表达式</th>
				<th field="remarks" width="100" align="center" align="center">Job描述</th>
				<th field="beanClass" width="200" align="center" align="center">Job执行类</th>
				<th field="isConcurrent" width="60" align="center" align="center" formatter="FormatterIsConcurrent">顺序执行</th>
				<th field="springId" width="80" align="center" align="center">Job执行ID</th>
				<th field="methodName" width="80" align="center" align="center">执行方法</th>
                <th field="createTime" width="120" align="center">创建时间</th>
            </tr>
        </thead>
    </table>
    <div id="tb" style="padding:0 20px;">
	    <div class="opt-buttons">
	        <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="newJob()">新建任务</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editJob()">编辑任务</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="destroyJob()">删除任务</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-print" onclick="changeJob()">启动|停止</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-sum" onclick="executeJob()">立即执行</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchTaskList()">刷新</a>
	    </div>
    </div>
</div>
<div id="divAdd" class="easyui-dialog" title="新建任务" data-options="closed:true,modal:true" style="width:360px;height:420px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" width="340px" style="margin:10px auto 0px auto;">
			<tr align="center">
				<th style="width:30%; text-align:right;">Job名称：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddJobName' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job分组：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddJobGroup' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Cron表达式：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddCronExpression' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job描述：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddRemarks' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job执行类：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddBeanClass' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">顺序执行：</th>
				<td style="width:70%; text-align:left;">
					<select id="txtAddIsConcurrent" class="input_2 txtinput1">
						<option value="0" selected="selected">否</option>
						<option value="1">是</option>
					</select>
				</td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job执行ID：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddSpringId' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">执行方法：</th>
				<td style="width:70%; text-align:left;"><input id='txtAddMethodName' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
	 			<td colspan="2" style="text-align: center;">
	 				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
	                                    onclick="addJobSubmit();">提交</a>
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
	                                    onclick="addJobCancel();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="divEdit" class="easyui-dialog" title="编辑任务" data-options="closed:true,modal:true" style="width:360px;height:420px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpEditing="0" cellspacing="0" width="340px" style="margin:10px auto 0px auto;">
			<tr align="center">
				<th style="width:30%; text-align:right;">Job名称：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditJobName' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job分组：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditJobGroup' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Cron表达式：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditCronExpression' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job描述：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditRemarks' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job执行类：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditBeanClass' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">顺序执行：</th>
				<td style="width:70%; text-align:left;">
					<select id="txtEditIsConcurrent" class="input_2 txtinput1">
						<option value="0" selected="selected">否</option>
						<option value="1">是</option>
					</select>
				</td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">Job执行ID：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditSpringId' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
				<th style="width:30%; text-align:right;">执行方法：</th>
				<td style="width:70%; text-align:left;"><input id='txtEditMethodName' type="text" class="input_2 txtinput1" /></td>
			</tr>
			<tr align="center">
	 			<td colspan="2" style="text-align: center;">
	 				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
	                                    onclick="editJobSubmit();">提交</a>
	                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
	                                    onclick="editJobCancel();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
</body> 
</html>
