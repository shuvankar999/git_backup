/*package com.tip.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.tip.service.CheckSSO;;

@Configuration
public class SSOClientConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.ca.wsdl");
		return marshaller;
	}

	@Bean
	public CheckSSO loginSSOClient(Jaxb2Marshaller marshaller) {
		final CheckSSO client = new CheckSSO();
		client.setDefaultUri("http://10.236.160.58:80/authazws/auth");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
*/