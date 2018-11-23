package com.zhang.shequ.utils.email.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.shequ.utils.util.ResourceUtil;

/**
 * 得到smtp地址
 */
public class SMTPUtil {
	
	private static final Logger log = LoggerFactory.getLogger(SMTPUtil.class);
	
	/**
	 * 简单的smtp生成，大部分是有用的，建议自己建立smtp库....
	 */
	public static String SimpleMailSender(String userName) {
		return  "smtp." + getHost(userName);
	}

	/**
	 * 获取SMPT地址
	 */
	public static String getSMTPAddress(String userName){
		String smtpAddress = null;
		try {
			//读取properties的内容
			smtpAddress = ResourceUtil.getResource("properties/smtp").getMap().get(getHost(userName).trim());
			//没有获取到
			if(smtpAddress == null){
				//生成简单得
				smtpAddress = SimpleMailSender(userName);
			}
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
		return smtpAddress;
	}
	
	/**
	 * 得到 邮箱@后面得字符    
	 */
	public static String getHost(String userName){
		return userName.split("@")[1];
	}
	
}