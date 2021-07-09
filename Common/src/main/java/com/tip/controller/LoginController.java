package com.tip.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ef.ese.pocketmobile.europe.service.LoginResponse;
import com.ge.ef.ese.pocketmobile.europe.service.LoginTestResponse;
import com.tip.configration.MOSClientConfiguration;
import com.tip.configration.MOSClientConfigurationLoginTest;
import com.tip.model.ErrorCd;
import com.tip.model.LoginRequest;
import com.tip.model.RoleAccess;
import com.tip.repository.UserRepository;
import com.tip.service.LoginClient;
import com.tip.service.LoginService;
import com.tip.service.LoginTestClient;
import com.tip.util.DecryptionUtil;



@Controller
public class LoginController {

	
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserRepository userRepository;
	
	 @RequestMapping(value="/ValidateUser",method = RequestMethod.POST)
	 @ResponseBody
	  public String getUsers(@RequestBody LoginRequest loginRequest) {
	    ObjectMapper mapper = new ObjectMapper();
	    String arrayToJson  = null;
	    LoginResponse response = null;
	    LoginTestResponse testresponse = null;
	    RoleAccess roleAccess=null;
	    HashMap<String, Object> userListmap = new HashMap<String, Object>();
        String ssoid= loginRequest.getSsoId();
        String appId= loginRequest.getAppCd();
        
	    String password = DecryptionUtil.decryptPassword(loginRequest.getPassword().trim());

	    try {
	    	
	    	if(appId.equalsIgnoreCase("MOS"))
	    	{
	            /*decode password For MOS*/
	    	    
	    		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MOSClientConfiguration.class);
				LoginClient loginClientPmals = context.getBean(LoginClient.class);
				response = loginClientPmals.getLoginDetails(ssoid,password);
				logger.info("++++++++++++++++++++++++++++++++++++++++"+response);
				logger.info("++++++++++++++++++++"+response.getLoginReturn().getAuthToken()+"++++++++++++++++++++");
				context.close();
				logger.info("??????????"+response.getLoginReturn().isAuthenticated());
				if(response.getLoginReturn().isAuthenticated()){
					try {
						arrayToJson=mapper.writeValueAsString(response);
						userListmap=loginService.retrieveUserNameDetailsMOS(ssoid);
						String empVal=userListmap.get("UserNameDetails").toString();
						empVal=empVal.substring(empVal.indexOf("=")+1,empVal.indexOf("}"));
						String EmplName='"'+"emplName"+'"';
						empVal=":"+'"'+empVal+'"';
						arrayToJson=arrayToJson.substring(0,arrayToJson.length()-2)+","+EmplName+empVal+"}}";
					} catch (Exception e) {
						logger.error("An error occurred : "+e);
					}
		    	}else {
					if (response.getLoginReturn().getErrorObject() != null && !response.getLoginReturn().getErrorObject().getItem().isEmpty()) {
						//String x = '"'+'{'+'"'+"errorObject : "+'"'+": "+'"'+"User doesnot have acess"+'"';
						//arrayToJson="{'errorObject': 'User doesnot have acess'}";
						String x = "{" + '"' + "Error_Cd" + '"' + ": "+'"'+response.getLoginReturn().getErrorObject().getItem().get(0).getCode();
						String y = "-"  + response.getLoginReturn().getErrorObject().getItem().get(0).getDescription() + '"' + "}";
						//errorMap.put(x, y);
						arrayToJson = x + y;
						//arrayToJson=errorMap.toString();
					}
				}
				
		    	// isAuthenticated - true - redirect to UI

	    	}else if(appId.equalsIgnoreCase("WKSHP"))
	    	{
	    		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MOSClientConfigurationLoginTest.class);
				LoginTestClient loginClientPmals = context.getBean(LoginTestClient.class);
				testresponse = loginClientPmals.getLoginDetails(ssoid,password);
				logger.info("++++++++++++++++++++++++++++++++++++++++"+testresponse);
				logger.info("++++++++++++++++++++"+testresponse.getLoginTestReturn().isAuthenticated()+"++++++++++++++++++++");
				context.close();
				if(testresponse.getLoginTestReturn().isAuthenticated()){
					roleAccess = loginService.getUserAccessBasedOnRole(ssoid);
					arrayToJson = mapper.writeValueAsString(roleAccess);
		    	}else
		    	{
			    	RoleAccess roleAccess1 = new RoleAccess();
		    		ErrorCd errorCd = new ErrorCd();
		    		errorCd.setErrorCode("AUTH_ERROR");
		        	errorCd.setErrorMessage("You are not authorized to access this site.");
		        	roleAccess1.setErrorCd(errorCd);
		        	roleAccess1.setAccessObjectList(null);
		    		arrayToJson = mapper.writeValueAsString(roleAccess1);
		    	}

	    	}
	    }
	    catch (Exception ex) {
	    	logger.error("Exception occurred : "+ex);
	     return "Exception Occured";
	    }
	    return  arrayToJson;
	  }
	  
	 
	 
	 
		/*@Bean
		CommandLineRunner  lookup(CheckSSO loginClient) {
			LoginResponse response = loginClient.getLoginDetails();
			System.out.println("??????????"+response.getReturn().getResultCode());
			return null;
			
		}*/
	
	
}
