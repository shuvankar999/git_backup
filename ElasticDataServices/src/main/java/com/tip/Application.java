package com.tip;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.tip.interceptor.ValidateRequestFilter;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.tip")
public class Application {

	static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    @Autowired
	DataSource dataSource;
    
    @Autowired
	private ValidateRequestFilter validateRequestFilter;
    
    public static void setEnv(Environment env) {
	}

    public static void main(String args[]) {
    	ConfigurableApplicationContext context = null;
		try{	
			context = SpringApplication.run(Application.class, args);
		}
		catch(Exception e){
			StackTraceElement stackTraceElement= e.getStackTrace()[0];
			logger.error("An error occurred while running main method : " + e);
			logger.error("At Line:" +stackTraceElement.getClassName()+":"+stackTraceElement.getLineNumber());
		}finally{
			if(null != context){
				context.registerShutdownHook();
			}
		}
    }
    
    public FilterRegistrationBean filterRegistrationBean () {
		   FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		   registrationBean.setFilter(validateRequestFilter);
		   return registrationBean;
	}
 }


