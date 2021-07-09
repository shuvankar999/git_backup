package com.tip.estimationmailreport.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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
import org.springframework.stereotype.Component;
/**
 * Email Utility
 */

@Component
public class MailUtil {
	
	static final Logger logger = LoggerFactory.getLogger(MailUtil.class);
	
	private String mailHost;
	private String mailSubject;
	private String mailFrom;
	private List<String> mailTo;
	private List<String> mailCC;
	private List<String> mailBCC;
	private String mailBody;
	private String imgFilePath;
	private String imgContentId;
	private String fileAttachmentPath;
	private String userName;
	private String password;

	public String sendEmail(){
		String msg = "SUCCESS";
		try{
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", this.mailHost);
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(this.mailFrom));
			
			mailTo(message);
			mailCc(message);
			mailBcc(message);
			
			message.setSubject(this.mailSubject);
			
			/// Add Mail Body
			MimeMultipart multipart = new MimeMultipart("related");
			Object messageBodyPart = new MimeBodyPart();
			((BodyPart) messageBodyPart).setContent(this.mailBody, "text/html");
			multipart.addBodyPart((BodyPart) messageBodyPart);

			// Add image to Mail Body if any
			if(this.imgFilePath!=null && !"".equals(this.imgFilePath)){
				MimeBodyPart imgPart=new MimeBodyPart();
				DataSource ds=new FileDataSource(this.imgFilePath);
				imgPart.setDataHandler(new DataHandler(ds));    
				imgPart.setHeader("Content-ID",this.imgContentId);
				multipart.addBodyPart(imgPart);
			}
	
			/// Add attachment if any
			if(this.fileAttachmentPath != null && !"".equals(this.fileAttachmentPath)){
				 addAttachmentInMail(multipart);
			}
			message.setContent(multipart);
			Transport.send(message);
		}catch(Exception e){
			msg = "FAILURE";
			logger.error("An error occurred while mailing Estimation report: ", e);
		}
		return msg;
	}
	
	public void mailTo(Message message) throws MessagingException{
		if(mailTo!=null && !mailTo.isEmpty()){
			for(String toAddress : mailTo){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
			}
		}
	}
	
	public void mailCc(Message message) throws MessagingException{
		if(mailCC!=null && !mailCC.isEmpty()){
			for(String ccAddress : mailCC){
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
			}
		}
	}

	public void mailBcc(Message message) throws MessagingException{
		if(mailBCC!=null && !mailBCC.isEmpty()){
			for(String bccAddress : mailBCC){
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress));
			}
		}
	}
	
	public void addAttachmentInMail(Multipart multipart) throws MessagingException
	{
		if(fileAttachmentPath.contains(",")){
			String[] fileNameArr = fileAttachmentPath.split(",");
			for(int i = 0; i<fileNameArr.length;i++)
			{
				if(fileNameArr[i] != null && !"".equals(fileNameArr[i])){
					attach(multipart,fileNameArr[i]);
				}
			}
		}else{
			attach(multipart,this.fileAttachmentPath);
		}
	}
	
	public void attach(Multipart multipart,String filePath) throws MessagingException
	{
		MimeBodyPart msgBodyPart = new MimeBodyPart();
	    Object source = new FileDataSource(filePath);
	    (msgBodyPart).setDataHandler(new DataHandler((DataSource)source));
        (msgBodyPart).setFileName( ((DataSource)source).getName());
	    multipart.addBodyPart((BodyPart)msgBodyPart);
	}
	public String getImgFilePath() {
		return imgFilePath;
	}
	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}
	public String getImgContentId() {
		return imgContentId;
	}
	public void setImgContentId(String imgContentId) {
		this.imgContentId = imgContentId;
	}
	public String getFileAttachmentPath() {
		return fileAttachmentPath;
	}
	public void setFileAttachmentPath(String fileAttachmentPath) {
		this.fileAttachmentPath = fileAttachmentPath;
	}
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public List<String> getMailTo() {
		return mailTo;
	}
	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}
	public List<String> getMailCC() {
		return mailCC;
	}
	public void setMailCC(List<String> mailCC) {
		this.mailCC = mailCC;
	}
	public List<String> getMailBCC() {
		return mailBCC;
	}
	public void setMailBCC(List<String> mailBCC) {
		this.mailBCC = mailBCC;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
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
}
