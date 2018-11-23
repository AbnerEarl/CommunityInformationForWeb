$(function(){
	searchTaskList();
});

function searchTaskList(){
	$("#quartzList").datagrid({
        url: '/scheduleJob/list'
    });
}

function FormatterJobStatus(value, row, index){
    if (value == 1) {
        return '<font color="green">正在运行</font>';
    } else {
        return '<font color="red">已停止</font>';
    }
}
 
function FormatterIsConcurrent(value, row, index){
    if (value == 1) {
		return '是';
    } else {
    	return '否';
    }
}

function newJob(){
	$("#divAdd").window('open');
    $("#txtAddJobName").val("");
    $("#txtAddJobGroup").val("");
    $("#txtAddCronExpression").val("");
    $("#txtAddRemarks").val("");
    $("#txtAddBeanClass").val("");
    $('#txtAddIsConcurrent').val(0);
    $("#txtAddSpringId").val("");
    $("#txtAddMethodName").val("");
}

function addJobCancel(){
	$("#divAdd").window('close');
}

function addJobSubmit(){
	if ($("#txtAddJobName").val().length == 0){
     	tip.tips("Job名称不能为空", "#txtAddJobName");
		return false;
	}
	if ($("#txtAddJobGroup").val().length == 0){
		tip.tips("Job分组不能为空", "#txtAddJobGroup");
		return false;
	}
	if ($("#txtAddCronExpression").length == 0){
		tip.tips("Cron表达式不能为空", "#txtAddCronExpression");
		return false;
	}
	if ($("#txtAddBeanClass").val().length == 0 && $("#txtAddSpringId").val().length == 0){
		tip.tips("Job执行类和执行ID不能同时为空", "#txtAddBeanClass");
		return false;
	}
	if ($("#txtAddMethodName").val().length == 0){
		tip.tips("Job执行方法不能为空", "#txtAddMethodName");
		return false;
	}
    var postData = {
		jobName: $("#txtAddJobName").val(),
		jobGroup: $("#txtAddJobGroup").val(),
		cronExpression: $("#txtAddCronExpression").val(),
		remarks: $("#txtAddRemarks").val(),
		beanClass: $("#txtAddBeanClass").val(),
		isConcurrent: $("#txtAddIsConcurrent").val(),
		springId: $("#txtAddSpringId").val(),
		status: 2,
		methodName: $("#txtAddMethodName").val()
	};
	load.post("/scheduleJob/add", postData, true, function(data){
        if(data.status == 0){
        	searchTaskList();
        	addJobCancel();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function editJob(){
    var rows = $("#quartzList").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择要编辑的数据");
        return false;
    }
    $("#txtEditJobName").val(rows[0].jobName);
    $("#txtEditJobGroup").val(rows[0].jobGroup);
    $("#txtEditCronExpression").val(rows[0].cronExpression);
    $("#txtEditRemarks").val(rows[0].remarks);
    $("#txtEditBeanClass").val(rows[0].beanClass);
    $('#txtEditIsConcurrent').val(rows[0].isConcurrent);
    $("#txtEditSpringId").val(rows[0].springId);
    $("#txtEditMethodName").val(rows[0].methodName);
    $("#divEdit").window('open');
}

function editJobCancel(){
	$("#divEdit").window('close');
}

function editJobSubmit(){
	if ($("#txtEditJobName").val()==''){
     	tip.tips("Job名称不能为空", "#txtEditJobName");
		return false;
	}
	if ($("#txtEditJobGroup").val()==''){
		tip.tips("Job分组不能为空", "#txtEditJobGroup");
		return false;
	}
	if ($("#txtEditCronExpression").val()==''){
		tip.tips("Cron表达式不能为空", "#txtEditCronExpression");
		return false;
	}
	if ($("#txtEditBeanClass").val()=='' && $("#txtEditSpringId").val()==''){
		tip.tips("Job执行类和执行ID不能同时为空", "#txtEditBeanClass");
		return false;
	}
	if ($("#txtEditMethodName").val()==''){
		tip.tips("Job执行方法不能为空", "#txtEditMethodName");
		return false;
	}
    var rows = $("#quartzList").datagrid('getChecked');
    var postData = {
    	id: rows[0].id,
		jobName: $("#txtEditJobName").val(),
		jobGroup: $("#txtEditJobGroup").val(),
		cronExpression: $("#txtEditCronExpression").val(),
		remarks: $("#txtEditRemarks").val(),
		beanClass: $("#txtEditBeanClass").val(),
		isConcurrent: $("#txtEditIsConcurrent").val(),
		springId: $("#txtEditSpringId").val(),
		methodName: $("#txtEditMethodName").val()
	};
	load.post("/scheduleJob/update", postData, true, function(data){
        if(data.status == 0){
        	searchTaskList();
        	editJobCancel();
        	tip.msg(data.msg);
        } else {
       		tip.msg(data.msg);
       	}
	});
}

function destroyJob(){
    var rows = $("#quartzList").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择一条要删除的任务");
        return false;
    }
	tip.confirm('确定删除任务？', function(){
		load.post("/scheduleJob/del", {jobId: rows[0].id}, true, function(data){
			if (data.status == 0){
				searchTaskList();
				tip.msg(data.msg);
			} else {
				tip.msg(data.msg);
			}
		});
	});
}

function changeJob(){
    var rows = $("#quartzList").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择一条要启动或停止的任务");
        return false;
    }
    if (rows[0].jobStatus == 1) {
    	tip.confirm('确定停止任务吗？', function(){
    		load.post("/scheduleJob/updateStatus", {jobId: rows[0].id, cmd: 'stop'}, true, function(data){
    			if (data.status == 0){
    				searchTaskList();
    				tip.msg(data.msg);
    			} else {
    				tip.msg(data.msg);
    			}
    		});
    	});
    } else {
    	tip.confirm('确定启动任务吗？', function(){
    		load.post("/scheduleJob/updateStatus", {jobId: rows[0].id, cmd: 'start'}, true, function(data){
    			if (data.status == 0){
    				searchTaskList();
    				tip.msg(data.msg);
    			} else {
    				tip.msg(data.msg);
    			}
    		});
    	});
    }
}

function executeJob(){
    var rows = $("#quartzList").datagrid('getChecked');
    if (rows.length != 1) {
        tip.msg("请选择一条要执行的任务");
        return false;
    }
	tip.confirm('确定立即执行任务？', function(){
		load.post("/scheduleJob/execute", {jobId: rows[0].id}, true, function(data){
			tip.msg(data.msg);
		});
	});
}
