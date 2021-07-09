package com.tip.asset.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.AssetSearchResponse;
import com.tip.asset.service.ThirdPartyAssetDataService;
import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DownloadAssetDetailsRequest;

@Service
public class ThirdPartyAssetDataServiceImpl implements ThirdPartyAssetDataService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;
    
    @Value("${elasticsearch.index_tp_asset}")
    private String esIndex;
    
    @Value("${elasticsearch.type_tp_asset}")
    private String esType;
    
    @Override
	public ArrayOfAssetData getTpAssetData(Map<String, String> fields) throws UnknownHostException {

    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfAssetData arrayOfAssetData = new ArrayOfAssetData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	AssetSearchResponse assetSearchResponse = setData(hit);
                arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        arrayOfAssetData.setCount(response.getHits().getTotalHits());
        return arrayOfAssetData;
    }
    
    private AssetSearchResponse setData(SearchHit hit) {
    	AssetSearchResponse assetSearchResponse = new AssetSearchResponse();
    	assetSearchResponse.setUnitNumber((Integer) hit.getSource().get("unit_nr"));
    	assetSearchResponse.setLicenceNumber((String) hit.getSource().get("unit_licence_nr"));
    	//assetSearchResponse.setOperationalStatus((String) hit.getSource().get("unit_operational_status_desc")); --not there
    	//assetSearchResponse.setInOutStatus((String) hit.getSource().get("unit_in_out_status_desc")); --not there
    	assetSearchResponse.setPhysicalCompanyNumber((Integer) hit.getSource().get("unit_physical_company_nr"));
    	//assetSearchResponse.setRespnsblCompanyNumber((Integer) hit.getSource().get("unit_respnsbl_company_nr"));--not there
    	//assetSearchResponse.setLastParkLocCd((String) hit.getSource().get("unit_last_park_loc_cd"));--not there
    	//assetSearchResponse.setLastIntchKey((Integer) hit.getSource().get("unit_last_intch_key"));--not there
    	
    	//assetSearchResponse.setCountry((String) hit.getSource().get("physical_country_name"));--not there
    	
    	assetSearchResponse.setCategoryGroupCode((String) hit.getSource().get("unit_catgrp_code"));
    	//assetSearchResponse.setRagStatus((String) hit.getSource().get("unit_status"));--not there
    	assetSearchResponse.setPhysicalBranchNumber((Integer) hit.getSource().get("unit_physical_branch_nr"));
    	//assetSearchResponse.setRespnsblBranchNumber((Integer) hit.getSource().get("unit_respnsbl_branch_nr"));--not there
    	assetSearchResponse.setSerialNumber((String) hit.getSource().get("unit_serial_nr"));
    	assetSearchResponse.setManufacturer((String) hit.getSource().get("unit_manufacturer"));
    	if("Y".equalsIgnoreCase((String) hit.getSource().get("unit_available_for_sale_ind"))) {
    		assetSearchResponse.setAvailableForSale("Yes");
    	}
    	else {
    		assetSearchResponse.setAvailableForSale("No");
    	}    	
    	//assetSearchResponse.setCustomerAssetNumber((String) hit.getSource().get("unit_customer_refrnc"));--not there
    	//assetSearchResponse.setLicenceCountryName((String) hit.getSource().get("unit_licence_country_name"));--not there
    	assetSearchResponse.setCustomerName((String) hit.getSource().get("customer_name"));
    	assetSearchResponse.setIsThirdPartyAsset("Yes");
		return assetSearchResponse;
	}

	@Override
    public Long getTpAssetDataCount(Map<String, String> fields) throws UnknownHostException {
    	Long count;
        Settings settings = Settings.builder().put("cluster.name", esClusterName)
                .put("client.transport.sniff", true)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        BoolQueryBuilder boolQuery = new BoolQueryBuilder(); 
		
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        count = response.getHits().getTotalHits();
       return count;
    }
    
    @Override
    public ArrayOfAssetData tpAssetDataTextSearch(String text) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();
        QueryBuilder qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
        		.field("unit_alpha_nr")
        		.field("unit_registr")
        		.field("unit_serial_nr")
        		.field("unit_cust_reference")
    			.field("chassis_number");
        SearchResponse response = client.prepareSearch(esIndex).setQuery(qb).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfAssetData arrayOfAssetData = new ArrayOfAssetData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	AssetSearchResponse assetSearchResponse = setData(hit);
                arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        arrayOfAssetData.setCount(response.getHits().getTotalHits());
        return arrayOfAssetData;
    }
    
    @Override
    public Long tpAssetDataTextSearchCount(String text) throws UnknownHostException {
    	Long count;
		TransportClient client = elasticUtil.getElasticSearchClient();
        QueryBuilder qb;
        
        if(text.matches("[0-9]+")){
        	qb = QueryBuilders.queryStringQuery("*"+ text + "*")
        			.field("unit_nr")
            		.field("unit_registr")
            		.field("unit_serial_nr")
            		.field("unit_cust_reference")
        			.field("chassis_number");
        }else if(text.matches("^[A-Za-z, ]++$")){
        	qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
        			.field("unit_alpha_nr")
            		.field("unit_registr")
            		.field("unit_serial_nr")
            		.field("unit_cust_reference")
        			.field("chassis_number");
        }else if(text.matches("[a-zA-Z0-9]*")){
        	qb = QueryBuilders.queryStringQuery("*"+ text + "*")
        			.field("unit_alpha_nr")
            		.field("unit_registr")
            		.field("unit_serial_nr")
            		.field("unit_cust_reference")
        			.field("chassis_number");
        }else{
        	qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
        			.field("unit_alpha_nr")
            		.field("unit_registr")
            		.field("unit_serial_nr")
            		.field("unit_cust_reference")
        			.field("chassis_number");
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(qb).setTypes(esType).setScroll(new TimeValue(60000)).get();
        response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        count = response.getHits().getTotalHits();
        return count;
    }
    
    @Override
	public ArrayOfAssetData getTpAssetDetailForExcel(DownloadAssetDetailsRequest downloadAssetDetailsRequest) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.filter(QueryBuilders.termsQuery("unit_nr", downloadAssetDetailsRequest.getUnitNrList()));
        /*for(Map<String, String> fields: fielsList)
        {
        	 BoolQueryBuilder boolQuery = new BoolQueryBuilder();
             for (Map.Entry<String, String> entry : fields.entrySet()){
                 boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
             }
             outerBoolQuery.should(boolQuery);
        }
        outerBoolQuery.minimumNumberShouldMatch(1);*/
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfAssetData arrayOfAssetData = new ArrayOfAssetData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	AssetSearchResponse assetSearchResponse = setData(hit);
                arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
            arrayOfAssetData.setCount(response.getHits().getTotalHits());
        } while (response.getHits().getHits().length != 0);
        return arrayOfAssetData;
    }
}