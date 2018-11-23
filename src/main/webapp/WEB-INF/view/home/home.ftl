<!DOCTYPE html> 
<html lang="en"> 
<head> 
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>后台管理系统</title> 
<link href="${request.contextPath}/static/content/css/common/base.css" rel="stylesheet">
<link href="${request.contextPath}/static/content/css/common/home.css" rel="stylesheet">
<link rel="stylesheet" href="${request.contextPath}/static/script/util/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/static/content/css/icon/icon.css">
<link href="${request.contextPath}/static/script/util/kindeditor/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${request.contextPath}/static/script/util/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/util/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/script/common/common.js"></script> 
<script type="text/javascript" src="${request.contextPath}/static/script/other/kindeditor.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/home/home.js"></script> 
</head> 
<body>
<div class="container">
    <div id="pf-hd">
        <div class="pf-logo">
            <img src="${request.contextPath}/static/content/images/main/main_logo.png" alt="logo">
        </div>
        <div class="pf-user">
            <div class="pf-user-photo">
                <img src="${avatar}" width="40" height="40" alt="">
            </div>
            <h4 class="pf-user-name ellipsis">${nickname}</h4>
            <i class="iconfont xiala">&#xe607;</i>
            <div class="pf-user-panel">
                <ul class="pf-user-opt">
                    <li class="pf-modify-info">
                        <a href="javascript:;">
                            <i class="iconfont">&#xe60d;</i>
                            <span class="pf-opt-name">个人信息</span>
                        </a>
                    </li>
                    <li class="pf-modify-pwd">
                        <a href="javascript:;">
                            <i class="iconfont">&#xe634;</i>
                            <span class="pf-opt-name">修改密码</span>
                        </a>
                    </li>
                    <li class="pf-logout">
                        <a href="#">
                            <i class="iconfont">&#xe60e;</i>
                            <span class="pf-opt-name">退出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="pf-bd">
        <div id="pf-sider">
            <h2 class="pf-model-name">
                <span class="iconfont">&#xe64a;</span>
                <span class="pf-name">功能菜单</span>
                <span class="toggle-icon"></span>
            </h2>
            <ul class="sider-nav">
             </ul> 
        </div>
        <div id="pf-page">
            <div class="easyui-tabs1 easyui-tabs" style="width:100%;height:100%;">
              	<div title="首页">
              		<iframe class="page-iframe" name="index" src="/index" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>
             	</div>
            </div>
        </div>
    </div>
    <div id="pf-ft">
        <div class="system-name">
          <i class="iconfont">&#xe6fe;</i>
          <span>研发中心共青团团务管理系统</span>
        </div>
        <div class="copyright-name">
          <span>CopyRight&nbsp;2018&nbsp;&nbsp;www.******.com&nbsp;版权所有</span>
          <i class="iconfont" >&#xe6ff;</i>
        </div>
    </div>
</div>
<div id="dlg" class="easyui-dialog" title="密码修改" data-options="closed:true,modal:true" style="width:320px;height:240px;">
	<div class="cztable" data-options="region:'center'">
		<table cellpadding="0" cellspacing="0" border="0" width="300px" style="margin:10px auto 0px auto;">
			<tr>
				<td style="width:30%; text-align:right;">原密码:</td>
				<td style="width:70%; text-align:left;"><input type="password" id="oldPass" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">新密码:</td>
				<td style="width:70%; text-align:left;"><input type="password" id="newPass" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td style="width:30%; text-align:right;">密码确认:</td>
				<td style="width:70%; text-align:left;"><input type="password" id="confirmPass" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
	 			<td colspan="2" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="submitPassword();">提交</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="closePassword();">取消</a>
	 			</td>
	 		</tr>
		</table>
	</div>
</div>
<div id="info" class="easyui-dialog" title="个人信息" data-options="closed:true,modal:true" >
	<table class="kv-table">
		<tbody>
			<tr>
				<td class="kv-label">头像</td> 
				<td class="kv-content">
					<img src="${request.contextPath}/static/content/images/main/user.png" id="info_avatar_img" width="40" height="40" alt="">
					<input type="hidden" id="info_avatar" />
	            	<a href="javascript:void(0)" class="easyui-linkbutton oneFileUpload">文件上传</a>
				</td>
				<td class="kv-label">用户名</td>
				<td class="kv-content" id="info_username"></td>
			</tr>
			<tr>
				<td class="kv-label">姓名</td>
				<td class="kv-content" id="info_name"></td>
				<td class="kv-label">身份证</td>
				<td class="kv-content" id="info_idcard"></td>
			</tr>
			<tr>
				<td class="kv-label">角色名称</td>
				<td class="kv-content" id="info_roleName"></td>
				<td class="kv-label">用户状态</td>
				<td class="kv-content" id="info_status"></td>
			</tr>
			<tr>
				<td class="kv-label">性别</td>
				<td class="kv-content">
					<select class="input_2 txtinput1" id="info_sex">
	   			  		<option value="1">男</option>
	   			  		<option value="2">女</option>
	   				</select>
				</td>
				<td class="kv-label">地址</td>
				<td class="kv-content"><input type="text" id="info_address" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
				<td class="kv-label">联系电话</td>
				<td class="kv-content"><input type="text" id="info_phone" class="input_2 txtinput1" /></td>
				<td class="kv-label">邮箱</td>
				<td class="kv-content"><input type="text" id="info_email" class="input_2 txtinput1" /></td>
			</tr>
			<tr>
	 			<td colspan="4" class="kv-content" style="text-align: center;">
	 				<a class="easyui-linkbutton" href="javascript:;" onclick="updateUser();">修改</a>
	                <a class="easyui-linkbutton" href="javascript:;" onclick="closeUser();">取消</a>
	 			</td>
			</tr>
		</tbody>
	</table>
</div>
</body> 
</html>
