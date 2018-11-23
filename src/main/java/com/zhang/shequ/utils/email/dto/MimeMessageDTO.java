package com.zhang.shequ.utils.email.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zhang.shequ.utils.util.ResourceUtil;

/**
 * 发送邮件的实体类
 */
public class MimeMessageDTO {
	
	private static Map<String, String> PUBLC_COMMON = ResourceUtil.getResource("properties/common").getMap();
	
	private String userName;//邮箱用户名
	
	private String password;//邮箱密码
	
	private String targetAddress;//目标邮箱地址
	
	private String subject;//邮件标题
	
	private Date sentDate;//邮件日期
	
	private String text;//邮件内容
	
	private List<String> filepath;//多个附件
	
	public static MimeMessageDTO init(String targetAddress, String subject, String text) {
		return new MimeMessageDTO(targetAddress,subject,text);
	}
	
	public MimeMessageDTO() {
		super();
	}

	public MimeMessageDTO(String userName, String password,String targetAddress, 
			String subject, String text, List<String> filepath) {
		super();
		this.userName = userName;
		this.password = password;
		this.targetAddress = targetAddress;
		this.subject = subject;
		this.sentDate = new Date();
		this.text = text;
		this.filepath = filepath;
	}

	public MimeMessageDTO(String targetAddress, String subject, String text) {
		super();
		this.userName = PUBLC_COMMON.get("mail.username").trim();
		this.password = PUBLC_COMMON.get("mail.password").trim();
		this.targetAddress = targetAddress;
		this.subject = subject;
		this.sentDate = new Date();
		this.text = text;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTargetAddress() {
		return targetAddress;
	}

	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getFilepath() {
		return filepath;
	}

	public void setFilepath(List<String> filepath) {
		this.filepath = filepath;
	}

	public void addFile(String fileName) {
		if (filepath == null){
			filepath = new ArrayList<String>();
		}
		filepath.add(fileName);
	}
	
}