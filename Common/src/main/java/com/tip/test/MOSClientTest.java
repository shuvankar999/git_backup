/*package com.tip.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ge.ef.ese.pocketmobile.europe.service.LoginResponse;
import com.tip.configration.MOSClientConfiguration;
import com.tip.service.LoginClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MOSClientTest.class)
@WebAppConfiguration
public class MOSClientTest {

	
	
	@Test
	public void testMOSClientTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MOSClientConfiguration.class);
		LoginClient loginClient = context.getBean(LoginClient.class);
		LoginResponse response = loginClient.getLoginDetails();
		System.out.println("++++++++++++++++++++++++++++++++++++++++"+response);
		System.out.println("++++++++++++++++++++"+response.getLoginReturn().getAuthToken()+"++++++++++++++++++++");
		for (int i = 0; i < response.getLoginReturn().getErrorObject().getItem().size(); i++) {
			System.out.println(response.getLoginReturn().getErrorObject().getItem().get(i).getCode());
			System.out.println(response.getLoginReturn().getErrorObject().getItem().get(i).getDescription());
			
		}
		
		context.close();
	}
	
	
	
}
*/