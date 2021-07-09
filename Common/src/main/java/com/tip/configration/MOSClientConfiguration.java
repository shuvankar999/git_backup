package com.tip.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.tip.service.LoginClient;

@Configuration
public class MOSClientConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.ge.ef.ese.pocketmobile.europe.service");
		return marshaller;
	}

	@Bean
	public LoginClient loginMOSClient(Jaxb2Marshaller marshaller) {
		final LoginClient client = new LoginClient();
		client.setDefaultUri("https://www.tip-europe.com/UAT/PMALSCheckInServices/PocketMobileService");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
