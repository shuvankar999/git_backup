package com.tip.report.util;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class MailUtilTest {
	
	MailUtil mailUtil;
	
	@Before
	public void beforesetup(){
		mailUtil = new MailUtil();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void sendEmail(){
		
		ArrayList<String> mailList=new ArrayList<String>();  
		mailList.add("abc.xyz@tipeurope.com");
		mailList.add("abc.xyz@tipeurope.com");
		
		mailUtil.setMailTo(mailList);
		mailUtil.setMailCC(mailList);
		mailUtil.setMailBCC(mailList);
		mailUtil.setFileAttachmentPath("/opt2/shared/data/MatrixReports/MaintenanceInspection/");		
		mailUtil.setImgFilePath("/opt2/shared/data/MatrixReports/MaintenanceInspection/abc.png");
		mailUtil.setImgContentId("2");
		mailUtil.setMailBody("Body");
		
		mailUtil.setMailFrom("abc.xyz@tipeurope.com");
		mailUtil.setMailHost("10.20.30.12");
		mailUtil.setMailSubject("Subject");
		mailUtil.setPassword("pass");
		mailUtil.setUserName("user");
		mailUtil.getPassword();
		mailUtil.getUserName();
		mailUtil.getImgFilePath();
		mailUtil.getImgContentId();
		mailUtil.getMailSubject();
		mailUtil.getFileAttachmentPath();
		mailUtil.getMailHost();
		mailUtil.getMailFrom();
		mailUtil.getMailTo();
		mailUtil.getMailCC();
		mailUtil.getMailBCC();
		mailUtil.getMailBody();
		
		try{
			mailUtil.sendEmail();
		}catch(Exception e){}		
		
		mailList=new ArrayList<String>();  
		mailList.add("abc.xyz@tipeurope.com");
		mailUtil.setMailTo(mailList);
		mailUtil.setMailCC(mailList);
		mailUtil.setMailBCC(mailList);
		mailUtil.setFileAttachmentPath("/opt2/shared/data/MatrixReports/MaintenanceInspection/,/opt2/shared/data/MatrixReports/MaintenanceInspection/");
		try{
			mailUtil.sendEmail();
		}catch(Exception e){}		
		
		mailList=new ArrayList<String>();
		mailUtil.setMailTo(mailList);
		mailUtil.setMailCC(mailList);
		mailUtil.setMailBCC(mailList);
		mailUtil.setFileAttachmentPath(",");		
		mailUtil.setImgFilePath("");
		try{
			mailUtil.sendEmail();
		}catch(Exception e){}
		
		mailUtil.setMailTo(null);
		mailUtil.setMailCC(null);
		mailUtil.setMailBCC(null);
		mailUtil.setFileAttachmentPath(null);		
		mailUtil.setImgFilePath(null);
		try{
			mailUtil.sendEmail();
		}catch(Exception e){}
	}
}
