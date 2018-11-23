<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<meta content="telephone=no" name="format-detection" />
<title>个人中心</title>
<link href="${request.contextPath}/static/protal/css/main.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/style.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/animate.min.css" rel="stylesheet" type="text/css">
<link href="${request.contextPath}/static/protal/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${request.contextPath}/static/protal/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/layer/layer.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/protal/js/wo.js"></script>
</head>
<body>
<div class="warpe">
    <div class="head">
        <a href="/protal/index" class="return"><i class="icon-chevron-left"></i> 返回</a>
        个人中心
    </div>
    <div class="interest_list" style="border-bottom: 1rem #ededed solid;">
        <ul>
            <li class="animated bounceInLeft clear_border">
                <a href="/protal/user/admin">
                    <img src="${avatar}">
                    <div class="list_r">
                        <p><span>${name}</span></p>
                        <p>${address}</p>
                        <div class="useradmin">
                            <p>账户管理<i class="icon-angle-right"></i> </p>
                            <span class="color_r">${(status==0)?string('正常','未认证')}</span>
                        </div>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="inter_add" style="border-bottom: 1rem #ededed solid;">
        <ul>
            <li class="animated bounceInLeft">
                <a href="/protal/house/new/my">
                    <span class="puff_left">我的新房</span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInLeft">
                <a href="/protal/house/old/my">
                    <span class="puff_left">我的二手房</span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated bounceInLeft">
                <a href="/protal/house/lease/my">
                    <span class="puff_left">我的出租房</span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
        </ul>
    </div>
    <div class="inter_add">
        <ul>
            <li class="animated fadeInLeft">
                <a href="/protal/second/second/my">
                    <span class="puff_left">我的二手商品</span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
            <li class="animated fadeInLeft">
                <a href="/protal/farm/order">
                    <span class="puff_left">我的农场订单</span>
                    <i class="icon-angle-right"></i>
                </a>
            </li>
        </ul>
    </div>
    <div class="id_bth inersest_bth animated bounceIn">
        <a href="/protal/logout">退 出</a>
    </div>
</div>
</body>
</html>