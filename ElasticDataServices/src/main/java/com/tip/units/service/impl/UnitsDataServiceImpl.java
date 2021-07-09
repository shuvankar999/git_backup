package com.tip.units.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.elastic.util.ElasticUtil;
import com.tip.units.model.ArrayOfUnitsData;
import com.tip.units.model.UnitsData;
import com.tip.units.service.UnitsDataService;


@Service
public class UnitsDataServiceImpl implements UnitsDataService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;
    
    @Value("${elasticsearch.index_units}")
    private String esIndex;
    
    @Value("${elasticsearch.type_units}")
    private String esType;
    
	public ArrayOfUnitsData getUnitsData(Map<String, Object> fields) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        for (Map.Entry<String, Object> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfUnitsData arrayOfUnitData = new ArrayOfUnitsData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	UnitsData unitsData = setData(hit);
                arrayOfUnitData.getUnitsDataItem().add(unitsData);
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        return arrayOfUnitData;
    }
    
    public Long getUnitsDataCount(HashMap<String, Object> fields) throws UnknownHostException {
    	Long count = 0L;
    	TransportClient client = elasticUtil.getElasticSearchClient();
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        BoolQueryBuilder boolQuery = new BoolQueryBuilder(); 
		
        for (Map.Entry<String, Object> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        count = response.getHits().getTotalHits();
       return count;
    }
    
    public ArrayOfUnitsData unitsDataTextSearch(String text) throws UnknownHostException {
    	String allField = "_all";
    	TransportClient client = elasticUtil.getElasticSearchClient();
        //MultiMatchQueryBuilder mmqb = QueryBuilders.multiMatchQuery(text, allField);
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(allField, text);
        SearchResponse response = client.prepareSearch(esIndex).setQuery(queryBuilder).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfUnitsData arrayOfUnitData = new ArrayOfUnitsData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	UnitsData unitsData = setData(hit);
                arrayOfUnitData.getUnitsDataItem().add(unitsData);
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
            //arrayOfUnitData.setCount(response.getHits().getTotalHits());
        } while (response.getHits().getHits().length != 0);
        return arrayOfUnitData;
    }
    
    private UnitsData setData(SearchHit hit) {
    	UnitsData unitsData = new UnitsData();
    	unitsData.setBranchNr((Integer) hit.getSource().get("branch_nr"));
    	unitsData.setCompanyNr((Integer) hit.getSource().get("company_nr"));
    	unitsData.setCustomerNr((Integer) hit.getSource().get("customer_nr"));
    	unitsData.setUnitCd((String) hit.getSource().get("unit_cd"));
    	unitsData.setUnitNr((Integer) hit.getSource().get("unit_nr"));
    	unitsData.setCatgrId((Integer) hit.getSource().get("catgr_id"));
    	unitsData.setCatgrpCd((String) hit.getSource().get("catgrp_cd"));
    	unitsData.setCurrencyCd((String) hit.getSource().get("currency_cd"));
    	unitsData.setUnitGroupCd((String) hit.getSource().get("unit_group_cd"));
    	unitsData.setUnitDesc((String) hit.getSource().get("unit_desc"));
		return unitsData;
	}

	@Override
    public Long unitsDataTextSearchCount(String text) throws UnknownHostException {
    	Long count = 0L;
    	String allField = "_all";
    	TransportClient client = elasticUtil.getElasticSearchClient();
        //MultiMatchQueryBuilder mmqb = QueryBuilders.multiMatchQuery(text, allField);
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(allField, text);
        SearchResponse response = client.prepareSearch(esIndex).setQuery(queryBuilder).setTypes(esType).setScroll(new TimeValue(60000)).get();
        response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        count = response.getHits().getTotalHits();
        return count;
    }
}