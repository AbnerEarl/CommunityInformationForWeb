package com.zhang.shequ.utils.email.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件 账号 密码
 */
public class PopupAuthenticator extends Authenticator {
	
	private String username = null;
	
	private String password = null;

	public PopupAuthenticator(String user, String pass) {
		this.username = user;
		this.password = pass;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
	
}
