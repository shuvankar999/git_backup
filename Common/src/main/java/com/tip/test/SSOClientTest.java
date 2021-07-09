/*package com.tip.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ca.wsdl.LoginResponse;
import com.tip.configration.SSOClientConfiguration;
import com.tip.service.CheckSSO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SSOClientTest.class)
@WebAppConfiguration
public class SSOClientTest {
	
	@Test
	public void testSSOClientTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SSOClientConfiguration.class);
		CheckSSO checkSSO = context.getBean(CheckSSO.class);
		LoginResponse response = checkSSO.getLoginDetails();
		System.out.println("++++++++++++++++++++++++++++++++++++++++"+response);
		System.out.println("++++++++++++++++++++"+response.getReturn().getMessage()+"++++++++++++++++++++");	
		context.close();
	}
	
	
	
}
*/