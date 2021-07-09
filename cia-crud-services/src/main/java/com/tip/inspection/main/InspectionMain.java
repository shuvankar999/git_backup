package com.tip.inspection.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tip.interceptor.ValidateRequestFilter;

@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@ComponentScan(basePackages = "com.tip")
	public class InspectionMain {

	    static final Logger logger = LoggerFactory.getLogger(InspectionMain.class);

	   /*@Autowired
	    private static Environment env;*/

	    /*@Autowired
		DataSource dataSource;*/
	    
	    @Autowired
		private ValidateRequestFilter validateRequestFilter;

	    /*public static void setEnv(Environment env) {
	    	InspectionMain.env = env;
	    }*/

	    public static void main(String[] args) {
	        ConfigurableApplicationContext context = null;
	        try {
	            context = SpringApplication.run(InspectionMain.class, args);
	        } catch (Exception e) {
	            logger.error("An error occurred while running main method : " + e);
	        } finally {
	            if (null != context) {
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
