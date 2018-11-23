var userId;
$(function(){
	loadMenu();
	bindEvent();
	resize();
	$(".oneFileUpload").initOnePicUpload();
});

function resize(){
    $(window).resize(function(){
        $('.tabs-panels').height($("#pf-page").height()-46);
        //$('.panel-body').height($("#pf-page").height()-76)
    }).resize();
}

function loadMenu(){
	load.get('/menu/getMenuList', false, function(data){
		getLeftMenu(data);
		resizeTopMenu();
	});
}

function getLeftMenu(data){
	var menus = JSON.parse(data);
	$(".sider-nav").html("");
	$(menus).each(function (i, menu) {
		var item = $('<li><a href="javascript:;"><span class="iconfont sider-nav-icon">'+ menu.icon +'</span><span class="sider-nav-title">'+ menu.text +'</span><i class="iconfont">&#xe642;</i></a></li>');
		var ul = $('<ul class="sider-nav-s"></ul>');
		$(menu.children).each(function (j, submenu) {
			var subitem = $('<li></li>');
			subitem.append('<a href="javascript:;" title="'+ submenu.attributes.url +'" data-id="'+ submenu.id +'">'+ submenu.text +'</a>');
			ul.append(subitem);
		});
		if(i ==0){
			item.addClass('current');
		}
		item.append(ul);
		$(".sider-nav").append(item);
	});
}

function resizeTopMenu(){
	var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;
    if(pages === 0){
        $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){
        if($(this).hasClass('disabled')) return;
        if($(this).hasClass('pf-nav-next')){
        	page++;
	        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
	        if(page == pages){
	            $(this).addClass('disabled');
	            $('.pf-nav-prev').removeClass('disabled');
	        }else{
	            $('.pf-nav-prev').removeClass('disabled');
	        }
        }else{
	        page--;
	        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
	        if(page == 0){
	            $(this).addClass('disabled');
	            $('.pf-nav-next').removeClass('disabled');
	        } else{
	            $('.pf-nav-next').removeClass('disabled');
	        }
        }
    });
}

function bindEvent(){
	
	// 左边二级菜单单击事件
	$(document).on('click', '.sider-nav-s li', function() {
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
        if($(".easyui-tabs1").tabs("exists",$(this).find('a').text())){
        	$(".easyui-tabs1").tabs("select",$(this).find('a').text());
        }else{
        	$(".easyui-tabs1").tabs("add",{
		       title: $(this).find('a').text(),
		       closable:true,
               content:'<iframe class="page-iframe" src="'+$(this).find('a').attr('title')+'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>'
		   });
        }
    });

	// 左边一级菜单单击事件
    $(document).on('click', '.sider-nav li', function() {
        $(this).siblings().removeClass('current');
        $(this).addClass('current');
    });

	//退出系统
    $(document).on('click', '.pf-logout', function() {
    	tip.confirm('您确定要退出吗？', function(){
    		window.location.href="/logout";
    	});
    });
    
    //左侧菜单收起
    $(document).on('click', '.toggle-icon', function() {
        $(this).closest("#pf-bd").toggleClass("toggle");
        setTimeout(function(){
        	$(window).resize();
        },300)
    });

	//修改密码
    $(document).on('click', '.pf-modify-pwd', function() {
    	$("#oldPass").val('');
    	$("#newPass").val('');
    	$("#confirmPass").val('');
        $("#dlg").dialog("open");
    });
    
    //个人信息
    $(document).on('click', '.pf-modify-info', function() {
    	load.get('/user/getUser', false, function(response){
    		if(response.status == 0){
    			var data = response.data;
    			$("#info_avatar_img").attr("src", data.avatar);
    			$("#info_avatar").val(data.avatar);
    			$("#info_username").html(data.username);
    			$("#info_name").html(data.name);
    			$("#info_idcard").html(data.idcard);
    			$("#info_roleName").html(data.roleName);
    			$("#info_status").html(data.status == 0 ? "可用" : "不可用");
    			$("#info_sex").val(data.sex);
    			$("#info_address").val(data.address);
    			$("#info_phone").val(data.phone);
    			$("#info_email").val(data.email);
    			userId = data.id;
    			$("#info").dialog("open");
    		}
    	});
    });
}

//取消修改密码
function closePassword(){
    $('#dlg').dialog('close');
}

//提交修改密码
function submitPassword(){
	if($("#oldPass").val().length == 0){
		tip.tips("原密码不能为空", "#oldPass");
		return false;
	}
	if($("#newPass").val().length == 0){
		tip.tips("新密码不能为空", "#newPass");
		return false;
	}
	if($("#confirmPass").val() != $("#newPass").val()){
		tip.tips("两次密码输入不一致", "#confirmPass");
		return false;
	}
	var data = {
		oldpwd: $("#oldPass").val(),
		newpwd: $("#newPass").val()
	}
	load.post('/user/updatePassword', data, true, function(data){
		if(data.status == 0){
			closePassword();
			tip.msg(data.msg);
		}else {
			tip.msg(data.msg);
		}
	});
}

function closeUser(){
    $('#info').dialog('close');
}

function updateUser(){
	if($("#info_phone").val().length == 0){
		tip.tips("手机号码不能为空", "#info_phone");
		return false;
	}else if(validate.cellPhone($("#info_phone").val())){
		tip.tips("请输入正确的手机号码", "#info_phone");
		return false;
	}
	if($("#info_email").val().length == 0){
		tip.tips("邮箱不能为空", "#info_email");
		return false;
	}else if(validate.email($("#info_email").val())){
		tip.tips("请输入正确的邮箱", "#info_email");
		return false;
	}
	var postData = {
		id: userId,
		avatar: $("#info_avatar").val(),
		sex: $("#info_sex").val(),
		address: $("#info_address").val(),
		nation: $("#info_nation").val(),
		education: $("#info_education").val(),
		phone: $("#info_phone").val(),
		email: $("#info_email").val()
	}
	load.post('/user/update', postData, true, function(data){
		if(data.status == 0){
			closeUser();
			tip.msg(data.msg);
		}else {
			tip.msg(data.msg);
		}
	});
}
