package com.tip;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ge.ef.ese.pocketmobile.europe.service.LoginResponse;




@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages="com.tip")
//public class TipLoginServiceApplication  implements CommandLineRunner{
public class TipLoginServiceApplication {


	
	 @Autowired
	DataSource dataSource;
	
/*	@Autowired
	private UserRepository userRepository;*/
	 
	public static void main(String[] args) {
		SpringApplication.run(TipLoginServiceApplication.class, args);
	}


/*	@Override
	public void run(String... args) throws Exception {
		System.out.println("DATASOURCE = " + dataSource);
		
		List<UserObj> list =userRepository.findAll();
		list.forEach(x -> System.out.println(x));
	}*/
	
	
	
	/*
	@Bean
	CommandLineRunner lookup(LoginClient loginClient,CheckSSO logincheckssoClient) {
		
			LoginResponse response = loginClient.getLoginDetails();
			com.ca.wsdl.LoginResponse CheckSSOresponse = logincheckssoClient.getLoginDetails();
			for (int i = 0; i < response.getLoginReturn().getErrorObject().getItem().size(); i++) {
				System.err.println(response.getLoginReturn().getErrorObject().getItem().get(i).getCode());
				System.err.println(response.getLoginReturn().getErrorObject().getItem().get(i).getDescription());
				System.err.println(response.getLoginReturn().isAuthenticated());
			}
			
			System.out.println("??????????"+CheckSSOresponse.getReturn().getResultCode());
			
			
			return null;
		};
		*/
		
}
