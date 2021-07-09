package com.tip.service;


import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.ge.ef.ese.pocketmobile.europe.service.AuthenticationResponse;
import com.ge.ef.ese.pocketmobile.europe.service.AuthenticationToken;
import com.ge.ef.ese.pocketmobile.europe.service.Login;
import com.ge.ef.ese.pocketmobile.europe.service.LoginResponse;

public class LoginClient extends WebServiceGatewaySupport  {
	
/*	public static void main(String[] args) {
		LoginClient td = new LoginClient();
		
		td.getLoginDetails();
	}*/

	public LoginResponse getLoginDetails(String ssoid ,String Password) {
		
		Login req = new Login();
		AuthenticationToken at = new AuthenticationToken();
		AuthenticationResponse aRes = new AuthenticationResponse();
		LoginResponse res = new LoginResponse();
		req.setBranchNr(62);
/*		at.setUserId("502352204");
		at.setPassword("Passw0rd!");*/
		at.setUserId(ssoid);
		at.setPassword(Password);
		req.setAuthToken(at);
		aRes =res.getLoginReturn();
		LoginResponse response = (LoginResponse) getWebServiceTemplate()
				.marshalSendAndReceive("https://www.tip-europe.com/SSO/MTX/UAT/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginRequest"));
				//.marshalSendAndReceive("http://10.236.228.36/SSO/MTX/UAT/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginTestRequest"));
				//.marshalSendAndReceive("https://www.tip-europe.com/SSO/MTX/PRD/PMALSCheckInServices/PocketMobileService",req,new SoapActionCallback("http://service.europe.pocketmobile.ese.ef.ge.com/PocketMobileService/loginRequest"));
		System.out.println("??????????"+response.getLoginReturn().isAuthenticated());
		return response;
	}
	
}
