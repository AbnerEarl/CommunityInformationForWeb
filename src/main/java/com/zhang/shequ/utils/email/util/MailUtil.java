package com.zhang.shequ.utils.email.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.shequ.utils.email.dto.MimeMessageDTO;

/**
 * 邮件发送
 */
public class MailUtil {
	
	private static final Logger log = LoggerFactory.getLogger(MailUtil.class);
	
	/**
	 * 发送邮件
	 * @param mimeDTO
	 * @return
	 */
	public static boolean sendEmail(MimeMessageDTO mimeDTO){
		return publicsendEmail(mimeDTO);
	}
	
	/**
	 * 邮件发送基础方法
	 * @param mimeDTO
	 * @param isGroup
	 * @return
	 */
	private static boolean publicsendEmail(MimeMessageDTO mimeDTO){
		Properties props = makeMailProperties(mimeDTO.getUserName());
		String hostname=SMTPUtil.getSMTPAddress(mimeDTO.getUserName());
		Session session = Session.getInstance(props, new PopupAuthenticator(mimeDTO.getUserName(), mimeDTO.getPassword()));
		session.setDebug(true);
		try {
			Transport ts = session.getTransport();
			ts.connect(hostname,mimeDTO.getUserName(),mimeDTO.getPassword());
			Message message =createEmail(session,mimeDTO);
			ts.sendMessage(message,message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			log.info(e.getMessage(),e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 创建邮件信息
	 * @param userName
	 * @return
	 */
	private static Properties makeMailProperties(String userName){
		Properties props = new Properties();
		String hostname=SMTPUtil.getSMTPAddress(userName);
		props.put("mail.smtp.host", hostname);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		if(hostname.indexOf(".qq.com")!=-1){
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		return props;
	}
	
	/**
	 * 创建邮件
	 * @return
	 */
	private static Message createEmail(Session session,MimeMessageDTO mimeDTO){
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(mimeDTO.getUserName()));
			  // 创建收件人列表  
	        if (null != mimeDTO.getTargetAddress() && mimeDTO.getTargetAddress().trim().length() > 0) {  
	            String[] arr = mimeDTO.getTargetAddress().split(",");  
	            int receiverCount = arr.length; 
	            if (receiverCount > 0) {
	            	InternetAddress[] address = new InternetAddress[receiverCount];  
	                for (int i = 0; i < receiverCount; i++) {  
	                    address[i] = new InternetAddress(arr[i]);  
	                }  
	                message.setRecipients(Message.RecipientType.TO, address);
	            }else{
	            	message.setRecipient(Message.RecipientType.TO, new InternetAddress(mimeDTO.getTargetAddress()));
	            }
	        }
			// 附件操作  
			if (null != mimeDTO.getFilepath() && mimeDTO.getFilepath().size() > 0) {  
				List<String> filepath = mimeDTO.getFilepath();
				// 后面的BodyPart将加入到此处创建的Multipart中  
				Multipart mp = new MimeMultipart(); 
				for (String filename : filepath) {  
					MimeBodyPart mbp = new MimeBodyPart();  
					// 得到数据源  
					FileDataSource fds = new FileDataSource(filename);  
					// 得到附件本身并至入BodyPart  
					mbp.setDataHandler(new DataHandler(fds));  
					// 得到文件名同样至入BodyPart  
					mbp.setFileName(fds.getName());  
					mp.addBodyPart(mbp);  
				}  
				MimeBodyPart mbp = new MimeBodyPart();  
				mbp.setText(mimeDTO.getText());  
				mp.addBodyPart(mbp);  
				// 移走集合中的所有元素  
				filepath.clear();  
				// Multipart加入到信件  
				message.setContent(mp);  
			} else {  
				// 设置邮件正文  
				message.setContent(mimeDTO.getText(),"text/html;charset=UTF-8");
			}
			message.setSubject(mimeDTO.getSubject());
			message.saveChanges();
		} catch (MessagingException e) {
			log.info(e.getMessage(),e);
		}
		return message;
	}
	
}