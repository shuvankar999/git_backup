package com.tip.service;

import javax.xml.bind.JAXBException;
import javax.xml.soap.MessageFactory;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import com.ge.ef.ese.pocketmobile.europe.service.LoginTest;
import com.ge.ef.ese.pocketmobile.europe.service.LoginTestResponse;

public class CheckIfValidUserTest {

	public static void main(String[] args) {

		LoginTest loginTest = new LoginTest();
		loginTest.setUserId("502352204");
		loginTest.setPassword("Passw0rd!");

		
		try {
			System.out.println("Checking if valid user - " + loginTest.getUserId());
			boolean isValidUser = isValidUser(loginTest);
			if(isValidUser)
				System.out.println("User Authenticated Successfully");
			else
				System.out.println("Invalid Username/Password.");
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static boolean isValidUser(LoginTest loginTest) throws JAXBException {

		LoginTestResponse response = new LoginTestResponse();
		try {
			SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory(MessageFactory.newInstance());
			messageFactory.afterPropertiesSet();

			WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

			marshaller.setContextPath("com.ge.ef.ese.pocketmobile.europe.service");
			marshaller.afterPropertiesSet();

			webServiceTemplate.setMarshaller(marshaller);
			webServiceTemplate.setUnmarshaller(marshaller);
			webServiceTemplate.afterPropertiesSet();

			response = (LoginTestResponse) webServiceTemplate
					.marshalSendAndReceive("https://www.tip-europe.com/UAT/PMALSCheckInServices/PocketMobileService.wsdl", loginTest);
			
		} catch (Exception s) {
			s.printStackTrace();
		}

		return response.getLoginTestReturn().isAuthenticated();
	}
}
