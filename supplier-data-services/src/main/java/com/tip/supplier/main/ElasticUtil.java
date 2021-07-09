package com.tip.supplier.main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ElasticUtil {
	
	static final Logger logger = LoggerFactory.getLogger(ElasticUtil.class);
	
	@Autowired
	private Environment env;
	
	private static TransportClient client = null;
	
	@PostConstruct
	public TransportClient getElasticSearchClient() throws UnknownHostException{
		if(client == null)
		{
			Settings settings = Settings.builder().put("cluster.name", env.getProperty("elasticsearch.clustername"))
	                .put("client.transport.sniff", true)
	                .build();
	        client = new PreBuiltTransportClient(settings);
	        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(env.getProperty("elasticsearch.host")), Integer.parseInt(env.getProperty("elasticsearch.port"))))
	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(env.getProperty("elasticsearch.host1")), Integer.parseInt(env.getProperty("elasticsearch.port1"))));
		}
        return client;
    }
	
	@PreDestroy
	public void destroy() {
		logger.info("Transport Client Destroy Called....");
		if(client != null)
		{
			client.close();
			logger.info("Transport Client Closed....");
		}
	  }
}
