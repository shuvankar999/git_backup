package com.tip.service;


import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.ge.ef.ese.pocketmobile.europe.service.AuthenticationResponse;
import com.ge.ef.ese.pocketmobile.europe.service.AuthenticationToken;
import com.ge.ef.ese.pocketmobile.europe.service.Login;
import com.ge.ef.ese.pocketmobile.europe.service.LoginResponse;
import com.ge.ef.ese.pocketmobile.europe.service.LoginTest;
import com.ge.ef.ese.pocketmobile.europe.service.LoginTestResponse;

public class LoginTestClient extends WebServiceGatewaySupport  {
	
	/*public static void main(String[] args) {
		LoginTestClient td = new LoginTestClient();
		
		td.getLoginDetails();
	}*/

	public LoginTestResponse getLoginDetails(String ssoid,String password) {
		
		LoginTest req = new LoginTest();
/*		req.setUserId("502352204");
		req.setPassword("Passw0rd!");*/
		req.setUserId(ssoid);
		req.setPassword(password);
		LoginTestResponse response = (LoginTestResponse) getWebServiceTemplate()
				.marshalSendAndReceive("https://www.tip-europe.com/SSO/MTX/UAT/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginTestRequest"));
				//.marshalSendAndReceive("http://10.236.228.36/SSO/MTX/UAT/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginTestRequest"));
				//.marshalSendAndReceive("https://test.tip-europe.com/SSO/MTX/UAT/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginTestRequest"));
				//.marshalSendAndReceive("https://www.tip-europe.com/SSO/MTX/PRD/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginTestRequest"));


		System.out.println("??????????"+response.getLoginTestReturn().isAuthenticated());
		return response;
	}
	
}
