package com.tip.asset.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.AssetSearchResponse;
import com.tip.asset.model.DynamicFilterRequest;
import com.tip.asset.model.Filter;
import com.tip.asset.service.AssetDataService;
import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DownloadAssetDetailsRequest;

@Service
public class AssetDataServiceImpl implements AssetDataService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;
    
    @Value("${elasticsearch.index_asset}")
    private String esIndex;
    
    @Value("${elasticsearch.type_asset}")
    private String esType;
    
    @Value("${elasticsearch.index_tp_asset}")
    private String esIndexTpAsset;
    
    @Value("${elasticsearch.type_tp_asset}")
    private String esTypeTpAsset;
    
    public static final Logger logger = LoggerFactory.getLogger(AssetDataServiceImpl.class);
    
    @Override
	public ArrayOfAssetData getAssetData(Map<String, String> fields) throws UnknownHostException {

    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
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
    
    @Override
	public ArrayOfAssetData getPaginatedAssetData(Map<String, String> fields,int from,int size) throws UnknownHostException {

    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setFrom(from).setSize(size).setExplain(true)
                .execute()
                .actionGet();
        ArrayOfAssetData arrayOfAssetData = new ArrayOfAssetData();
        for (SearchHit hit : response.getHits().getHits()) {
        	AssetSearchResponse assetSearchResponse = setData(hit);
            arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
        }
        arrayOfAssetData.setCount(response.getHits().getTotalHits());
        return arrayOfAssetData;
    }
    
    private AssetSearchResponse setData(SearchHit hit) {
    	AssetSearchResponse assetSearchResponse = new AssetSearchResponse();
    	assetSearchResponse.setUnitNumber((Integer) hit.getSource().get("unit_nr"));
    	assetSearchResponse.setLicenceNumber((String) hit.getSource().get("unit_licence_nr"));
    	assetSearchResponse.setOperationalStatus((String) hit.getSource().get("unit_operational_status_desc"));
    	assetSearchResponse.setInOutStatus((String) hit.getSource().get("unit_in_out_status_desc"));
    	assetSearchResponse.setPhysicalCompanyNumber((Integer) hit.getSource().get("unit_physical_company_nr"));
    	assetSearchResponse.setRespnsblCompanyNumber((Integer) hit.getSource().get("unit_respnsbl_company_nr"));
    	assetSearchResponse.setLastParkLocCd((String) hit.getSource().get("unit_last_park_loc_cd"));
    	assetSearchResponse.setLastIntchKey((Object) hit.getSource().get("unit_last_intch_key"));
    	assetSearchResponse.setCountry((String) hit.getSource().get("physical_country_name"));
    	assetSearchResponse.setCategoryGroupCode((String) hit.getSource().get("unit_catgrp_code"));
    	assetSearchResponse.setRagStatus((String) hit.getSource().get("unit_status"));
    	assetSearchResponse.setPhysicalBranchNumber((Integer) hit.getSource().get("unit_physical_branch_nr"));
    	assetSearchResponse.setRespnsblBranchNumber((Integer) hit.getSource().get("unit_respnsbl_branch_nr"));
    	assetSearchResponse.setSerialNumber((String) hit.getSource().get("unit_serial_nr"));
    	assetSearchResponse.setManufacturer((String) hit.getSource().get("unit_manufacturer"));
    	if("Y".equalsIgnoreCase((String) hit.getSource().get("unit_available_for_sale_ind"))) {
    		assetSearchResponse.setAvailableForSale("Yes");
    	}
    	else {
    		assetSearchResponse.setAvailableForSale("No");
    	}    	
    	assetSearchResponse.setCustomerAssetNumber((String) hit.getSource().get("unit_customer_refrnc"));
    	assetSearchResponse.setLicenceCountryName((String) hit.getSource().get("unit_licence_country_name"));
    	assetSearchResponse.setCustomerName((String) hit.getSource().get("customer_name"));
    	assetSearchResponse.setIsThirdPartyAsset("No");
		return assetSearchResponse;
	}

	@Override
    public Long getAssetDataCount(Map<String, String> fields) throws UnknownHostException {
    	Long count;

    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        BoolQueryBuilder boolQuery = new BoolQueryBuilder(); 
		
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        count = response.getHits().getTotalHits();
       return count;
    }
    
    @Override
    public ArrayOfAssetData assetDataTextSearch(String text) throws UnknownHostException {
    	TransportClient client = elasticUtil.getElasticSearchClient();
        QueryBuilder qb;
        if(checkIntValue(text)) {
        	qb = QueryBuilders.queryStringQuery("*"+ text + "*")
        			.field("unit_nr")
            		.field("unit_serial_nr")
            		.field("unit_licence_nr")
            		.field("unit_cd")
            		.field("unit_customer_refrnc");
        } else {
        	qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
            		.field("unit_serial_nr")
            		.field("unit_licence_nr")
            		.field("unit_cd")
            		.field("unit_customer_refrnc");
        }
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
    public Long assetDataTextSearchCount(String text) throws UnknownHostException {
    	Long count;

    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        QueryBuilder qb;
        if(text.matches("[0-9]+")){
        	qb = QueryBuilders.queryStringQuery("*"+ text + "*")
        			.field("unit_nr")
            		.field("unit_serial_nr")
            		.field("unit_licence_nr")
            		.field("unit_cd")
            		.field("po_number")
            		.field("unit_customer_refrnc").splitOnWhitespace(false);
        }else if(text.matches("^[A-Za-z, ]++$")){
        	qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
            		.field("unit_serial_nr")
            		.field("unit_licence_nr")
            		.field("unit_cd")
            		.field("po_number")
            		.field("unit_customer_refrnc").splitOnWhitespace(false);
        }else if(text.matches("[a-zA-Z0-9]*")){
        	qb = QueryBuilders.queryStringQuery("*"+ text + "*")
            		.field("unit_serial_nr")
            		.field("unit_licence_nr")
            		.field("unit_cd")
            		.field("po_number")
            		.field("unit_customer_refrnc").splitOnWhitespace(false);
        }else{
        	qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
            		.field("unit_serial_nr")
            		.field("unit_licence_nr")
            		.field("unit_cd")
            		.field("po_number")
            		.field("unit_customer_refrnc").splitOnWhitespace(false);
        }
        
        logger.info("query for asset and tpAsset---- \n"+qb.toString());
        SearchResponse response = client.prepareSearch(esIndex,esIndexTpAsset).setQuery(qb).setTypes(esType,esTypeTpAsset).setScroll(new TimeValue(60000)).get();
        response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        count = response.getHits().getTotalHits();
        return count;
    }
    
   @Override
	public ArrayOfAssetData getAssetDetailForExcel(DownloadAssetDetailsRequest downloadAssetDetailsRequest) throws UnknownHostException {

    	TransportClient client = elasticUtil.getElasticSearchClient();
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.filter(QueryBuilders.termsQuery("unit_nr", downloadAssetDetailsRequest.getUnitNrList()));
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
    
    public boolean checkIntValue(String val)
    {
    	boolean flag = true;
    	try{
    	Integer.parseInt(val);
    	}
    	catch(NumberFormatException e){
    		flag = false;
    	}
		return flag;
    }
    
    @Override
	public ArrayOfAssetData dynamicFilterAsset() throws UnknownHostException {
		DynamicFilterRequest dynamicFilterRequest = new DynamicFilterRequest();
		Filter filter1 = new Filter();
		filter1.setCondition("AND");
		filter1.setOperation("range");
		filter1.setRangeLt("40");
		filter1.setRangeGt("10");
		filter1.setIncludeLower(true);
		filter1.setIncludeUpper(true);
		filter1.setField("unit_physical_branch_nr");
		dynamicFilterRequest.getFilterLst().add(filter1);
		
		Filter filter2 = new Filter();
		filter2.setCondition("AND");
		filter2.setOperation("equals");
		filter2.setValue("FR");
		filter2.setField("unit_licence_country_cd");
		dynamicFilterRequest.getFilterLst().add(filter2);
		
		Filter filter3 = new Filter();
		filter3.setCondition("AND");
		filter3.setOperation("like");
		filter3.setValue("PLAN DE");
		filter3.setField("unit_status_comment");
		dynamicFilterRequest.getFilterLst().add(filter3);
		
		Filter filter4 = new Filter();
		filter4.setCondition("AND");
		filter4.setOperation("multiFilter");
		filter4.setField("unit_licence_nr");
		dynamicFilterRequest.getFilterLst().add(filter4);

		TransportClient client = elasticUtil.getElasticSearchClient();
		
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        for (Filter filter : dynamicFilterRequest.getFilterLst()){
        	if("AND".equals(filter.getCondition()))
        	{
        		if("equals".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders.matchQuery(filter.getField(), filter.getValue()));
        		} else if("range".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders
        					.rangeQuery(filter.getField())
        					/*.lt(Integer.parseInt(filter.getRangeLt()))
        					.gt(Integer.parseInt(filter.getRangeGt())));*/
        					.from(filter.getRangeGt())
        					.to(filter.getRangeLt())
        					.includeLower(filter.isIncludeLower())
                            .includeUpper(filter.isIncludeUpper()));
        		} else if("like".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders.matchPhraseQuery(filter.getField(), filter.getValue()));
        		} else if("multiFilter".equals(filter.getOperation())) {
        			//boolQuery.must(QueryBuilders.termsQuery(filter.getField(), "548AFW78","231VY45","9370VT45"));/*simpleQueryStringQuery(queryString)(query).field("newContent").field("T"))
        			BoolQueryBuilder innerBoolQuery = new BoolQueryBuilder();
        				innerBoolQuery.should(QueryBuilders.matchQuery(filter.getField(), "548AFW78"));
                        innerBoolQuery.should(QueryBuilders.matchQuery(filter.getField(), "231VY45"));
                        innerBoolQuery.should(QueryBuilders.matchQuery(filter.getField(), "9370VT45"));
                        innerBoolQuery.minimumNumberShouldMatch(1);
                    boolQuery.must(innerBoolQuery);
                    //.filter(QueryBuilders.termsQuery(Collection, "abc", "xyz"));*/
        		}
        	}
        	else if("OR".equals(filter.getCondition())) {
        		if("equals".equals(filter.getOperation())) {
        			boolQuery.should(QueryBuilders.matchQuery(filter.getField(), filter.getValue()));
        		} else if("range".equals(filter.getOperation())) {
        			boolQuery.should(QueryBuilders
        					.rangeQuery(filter.getField())
        					.from(filter.getRangeLt())
        					.to(filter.getRangeGt())
        					.includeLower(filter.isIncludeLower())
                            .includeUpper(filter.isIncludeUpper()));
        		} else if("like".equals(filter.getOperation())) {
        			boolQuery.should(QueryBuilders.matchPhraseQuery(filter.getField(), filter.getValue()));
        		}
        	}
        }
       // boolQuery.minimumNumberShouldMatch(1);
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfAssetData arrayOfAssetData = new ArrayOfAssetData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	System.out.println("unit_physical_branch_nr in range 10-40: "+ hit.getSource().get("unit_physical_branch_nr"));
            	System.out.println("unit_licence_country_cd equals: "+ hit.getSource().get("unit_licence_country_cd"));
            	System.out.println("unit_status_comment like : "+ hit.getSource().get("unit_status_comment"));
            	AssetSearchResponse assetSearchResponse = setData(hit);
                arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        arrayOfAssetData.setCount(response.getHits().getTotalHits());
        return arrayOfAssetData;
    }
}