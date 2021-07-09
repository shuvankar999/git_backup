package com.tip.elasticsearchLoad.service.impl;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.elastic.util.ElasticUtil;


@Service
public class ElasticSearchLoadServiceImpl{
	
	@Autowired
	private static ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
    private static String esHost = "10.236.185.7";

    @Value("${elasticsearch.port}")
    private static int esPort = 9300;

    @Value("${elasticsearch.clustername}")
    private static String esClusterName = "TIP-application";
    
    @Value("${elasticsearch.index_units}")
    private String esIndex;
    
    @Value("${elasticsearch.type_units}")
    private String esType;
    
	public static void loadData() throws UnknownHostException {
		
		TransportClient client = elasticUtil.getElasticSearchClient();
        
       /* for single data load
        * IndexResponse response = client.prepareIndex("books", "book", "1")
                .setSource(putJsonDocument("title", "content", new Date(), "author"))
                .execute()
                .actionGet();*/
        
        //for bulk data load
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex("books", "book", "1")
                .setSource(putJsonDocument("title", "content", new Date(), "author"))
                );

        bulkRequest.add(client.prepareIndex("books", "book", "2")
                .setSource(putJsonDocument("title", "content", new Date(), "author"))
                );
        
        bulkRequest.add(client.prepareIndex("books", "book", "3")
                .setSource(putJsonDocument("title", "content", new Date(), "author"))
                );
        bulkRequest.add(client.prepareIndex("books", "book", "4")
                .setSource(putJsonDocument("title", "content", new Date(), "author"))
                );
        bulkRequest.add(client.prepareIndex("books", "book", "5")
                .setSource(putJsonDocument("title", "content", new Date(), "author"))
                );

        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
    }
	
	public static Map putJsonDocument(String title, String 
			content, Date postDate, String author){
			Map jsonDocument = new HashMap<>();
			jsonDocument.put("title", title); 
			jsonDocument.put("conten", content); 
			jsonDocument.put("postDate", postDate); 
			jsonDocument.put("author", author);
			return jsonDocument; 
			}
	
	/*public static void main(String args[]){
		try {
			loadData();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}