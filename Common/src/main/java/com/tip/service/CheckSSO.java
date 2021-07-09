/*package com.tip.service;

import javax.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.ca.wsdl.IdentityContext;
import com.ca.wsdl.Login;
import com.ca.wsdl.LoginResponse;
import com.ca.wsdl.ObjectFactory;


public class CheckSSO extends WebServiceGatewaySupport  {
	
	public static void main(String[] args) {
		CheckSSO c = new CheckSSO();
		c.getLoginDetails();
	}
public LoginResponse getLoginDetails() {
		//Login b1 = new Login();
		//IdentityContext id = new IdentityContext();
		ObjectFactory objFactory = new ObjectFactory();
		Login objFactoryBean =  objFactory.createLogin();
		//IdentityContext id = objFactoryBean.getIdentityContext();
		IdentityContext id = new IdentityContext();
		id.setUserName("502352204");
		id.setPassword("Passw0rd!");
		b1.setIdentityContext(id);
		b1.setAppId("handheld1");
		b1.setResource("/handheld1/*");
		b1.setAction("NULL");
		
		id.setUserName("502352204");
		id.setPassword("Passw0rd!");
		objFactoryBean.setIdentityContext(id);
		objFactoryBean.setAppId("handheld1");
		objFactoryBean.setResource("/handheld1/*");
		objFactoryBean.setAction("NULL");
		
		JAXBElement<Login> request = new ObjectFactory().createLogin(objFactoryBean);
		
        @SuppressWarnings("unchecked")
        JAXBElement<LoginResponse> response = (JAXBElement<LoginResponse>) getWebServiceTemplate()
        .marshalSendAndReceive(request);
        System.out.println("??????????"+response.getValue().getReturn().getResultCode());
		
		LoginResponse response = (LoginResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://10.236.160.58:80/authazws/auth",b1,new SoapActionCallback("http://www.ca.com/siteminder/authaz/2010/04/15/authaz.xsd/AuthAZService/loginRequest"));

		System.out.println("??????????"+response.getReturn().getResultCode());
		return response.getValue();
	}



}
*/