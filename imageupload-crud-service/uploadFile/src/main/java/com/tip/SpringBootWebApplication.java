package com.tip;

/**
 * Created by 885155 on 6/14/2017.
 */

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootWebApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringBootWebApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = null;
        try {
            context = SpringApplication.run(SpringBootWebApplication.class, args);
        } catch (Exception e) {
            LOG.error("Error message.." + e);
        } finally {
            if (null != context) {
                context.registerShutdownHook();
            }
        }

    }


    //Tomcat large file upload connection reset
    //http://www.mkyong.com/spring/spring-file-upload-and-connection-reset-issue/
    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });

        return tomcat;

    }

}
