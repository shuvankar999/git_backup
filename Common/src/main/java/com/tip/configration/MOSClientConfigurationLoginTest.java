package com.tip.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.tip.service.LoginTestClient;

@Configuration
public class MOSClientConfigurationLoginTest {
	@Bean
	public Jaxb2Marshaller marshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.ge.ef.ese.pocketmobile.europe.service");
		return marshaller;
	}

	@Bean
	public LoginTestClient loginMOSClient(Jaxb2Marshaller marshaller) {
		final LoginTestClient client = new LoginTestClient();
		client.setDefaultUri("https://www.tip-europe.com/UAT/PMALSCheckInServices/PocketMobileService");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
