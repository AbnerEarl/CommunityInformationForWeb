<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>性别</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/shake.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/idangerous.swiper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
<script>
	$(function(){
		$(".inter_add li").click(function(){
			$(this).siblings().find("i").removeClass("selected").hide();
			$(this).find("i").addClass("selected").show();
			$("#my_sex").data('value', $(this).find("i").data('value'))
		})
	})
	
	function submitBtn(){
		tip.confirm("确认修改性别？", function(){
			load.post("/protal/user/update", {sex:$("#my_sex").data('value')}, true, function(data){
		        if (data.status == 0) {
		        	tip.msg(data.msg);
		            window.location.href = "/protal/user/admin";
		        } else {
		            tip.msg(data.msg);
		        }
			});
		});
	}
</script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="#" class="return"><i class="icon-chevron-left"></i> 返回</a>
      	  修改性别
        <a href="#" class="search animated fadeInRight" onclick="submitBtn()">提交</a>
    </div>
    <div class="I_div">
        <div class="car_t animated bounceInLeft">请选择您的性别</div>
        <div class="inter_add">
            <ul id="my_sex" data-value="1">
                <li class="animated bounceInLeft">
                    <a href="#">
                        <span class="puff_left">男</span>
                        <i class="icon-ok color_g2" data-value="1"></i>
                    </a>
                </li>
                <li class="animated bounceInRight clear_border">
                    <a href="#">
                        <span class="puff_left">女</span>
                        <i class="icon-ok color_g2" data-value="2" style="display: none;"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>