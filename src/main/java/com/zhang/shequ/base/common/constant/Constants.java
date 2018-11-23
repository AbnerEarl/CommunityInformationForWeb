package com.zhang.shequ.base.common.constant;

public class Constants {
	
	public final static String USER_SESSION_KEY = "USERDTO_SESSION_KEY";//用户信息Session
	
	public final static String USER_COOKIE_KEY = "USERDTO_COOKIE_KEY";//用户信息Cookie
	
	public final static String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";//验证码
	
	public final static String CART_FARM_SESSION_KEY = "_CART_FARM_SESSION_KEY_";//农场购物车
	
	public static final int USER_SESSION_EXPIRE = 60*60*6;//用户信息Session有效时间
	
	public static final int USER_COOKIE_EXPIRE = 60*60*24*7;//用户信息Session有效时间
	
	public final static String DEAFAULT_PASSWORD = "12345";//默认重置密码
	
	public final static String DEAFAULT_MESSAGE_CODE = "000000";//默认短信验证码
	
	public final static String DEAFAULT_AVATAR = "/static/content/images/main/user.png";//默认头像
	
	public final static String ADMIN_NAME = "admin";//默认超级管理员标识
	
	public final static Integer ADMIN_ID = 1; //超级管理员id
	
	public final static Integer RESIDENT_ID = 4; //普通居民id

	public final static Integer ADMIN_ROLE_ID = 1;//超级管理员角色id

}
