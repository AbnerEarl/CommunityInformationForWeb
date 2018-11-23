function loadData(){
	$('#dg').datagrid({  
   	    url: '/loginLog/getLoginLogListByPage?logname='+$("#txtLogname").val()
	}); 
}
