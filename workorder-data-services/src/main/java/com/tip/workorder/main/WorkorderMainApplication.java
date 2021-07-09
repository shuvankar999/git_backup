package com.tip.workorder.main;

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
public class WorkorderMainApplication {

    static final Logger logger = LoggerFactory.getLogger(WorkorderMainApplication.class);

    @Autowired
    private static Environment env;

    @Autowired
    DataSource dataSource;
    
    @Autowired
	private ValidateRequestFilter validateRequestFilter;

    public static void setEnv(Environment env) {
        WorkorderMainApplication.env = env;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = null;
        try {
            context = SpringApplication.run(WorkorderMainApplication.class, args);
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
