package com.tip.common.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import java.lang.Integer;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.common.model.DropDownRequest;
import com.tip.common.service.CommonService;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DropOption;

@Service
public class CommonServiceImpl implements CommonService{

	public static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.index_asset}")
    private String esIndexAsset;
    
    
    @Value("${elasticsearch.index_tp_asset}")
    private String esIndexTpAsset;
    
    @Value("${elasticsearch.type_asset}")
    private String esTypeAsset;
   
    
    @Value("${elasticsearch.type_tp_asset}")
    private String esTypeTpAsset;
    
    @Value("${elasticsearch.index_customer}")
    private String esIndexCustomer;
    
    @Value("${elasticsearch.type_customer}")
    private String esTypeCustomer;
    
    @Value("${elasticsearch.index_supplier}")
    private String esIndexSupplier;
    
    @Value("${elasticsearch.type_supplier}")
    private String esTypeSupplier;
    
	@Value("${elasticsearch.index_estimation}")
	private String esIndexEst;

	@Value("${elasticsearch.type_estimation}")
	private String esTypeEst;
    
    
    @Override
	public List getDistinctList(DropDownRequest dropDownRequest) {
		TransportClient client = null;
		long start_time = 0l;
		long end_time = 0l;

		String elsticDbColumn = new String(dropDownRequest.getElasticDbColumn());
		String entity = new String(dropDownRequest.getEntity());
		try {
			client = elasticUtil.getElasticSearchClient();
		} catch (UnknownHostException e) {
			logger.error("An error occurred during retrieval of distinct dropdown list" + e);
		}
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		createDynamicQuery(elsticDbColumn, boolQuery, dropDownRequest);
		SearchRequestBuilder aggregationQuery = null;
		if (entity.equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_SUPPLIER)) {
			SearchResponse responseCount = client.prepareSearch(esIndexSupplier).setQuery(boolQuery)
					.setTypes(esTypeSupplier).setScroll(new TimeValue(60000)).get();
			int nbHits = Math.toIntExact(responseCount.getHits().getTotalHits());
			System.out.println(nbHits);
			start_time = System.currentTimeMillis();
			aggregationQuery = client.prepareSearch(esIndexSupplier).setQuery(boolQuery).setTypes(esTypeSupplier)
					.addAggregation(AggregationBuilders.terms("distinct_" + elsticDbColumn)
							.field(elsticDbColumn + ".keyword").size(nbHits));
		} else if (entity.equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_CUSTOMER)) {
			Integer size;
			if(dropDownRequest.getText()==null || dropDownRequest.getText().isEmpty())
				size = dropDownRequest.getIndexSize();
			else {
				SearchResponse responseCount = client.prepareSearch(esIndexCustomer).setQuery(boolQuery)
						.setTypes(esTypeCustomer).setScroll(new TimeValue(60000)).get();
				int nbHits = Math.toIntExact(responseCount.getHits().getTotalHits());
				size = nbHits;
				System.out.println(nbHits);
			}
			
			aggregationQuery = client.prepareSearch(esIndexCustomer).setQuery(boolQuery).setTypes(esTypeCustomer)
					.addAggregation(AggregationBuilders.terms("distinct_" + elsticDbColumn)
							.field(elsticDbColumn + ".keyword").size(size));
		} else if (entity.equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_EQUIP)) {
			SearchResponse responseCount = client.prepareSearch(esIndexAsset, esIndexTpAsset).setQuery(boolQuery)
					.setTypes(esTypeAsset, esTypeTpAsset).setScroll(new TimeValue(60000)).get();
			int nbHits = Math.toIntExact(responseCount.getHits().getTotalHits());
			System.out.println(nbHits);
			aggregationQuery = client.prepareSearch(esIndexAsset, esIndexTpAsset).setQuery(boolQuery)
					.setTypes(esTypeAsset, esTypeTpAsset).addAggregation(AggregationBuilders
							.terms("distinct_" + elsticDbColumn).field(elsticDbColumn + ".keyword").size(nbHits));
		} else if (entity.equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_ESTIMATION)) {
			SearchResponse responseCount = client.prepareSearch(esIndexEst).setQuery(boolQuery).setTypes(esTypeEst)
					.setScroll(new TimeValue(60000)).get();
			int nbHits = Math.toIntExact(responseCount.getHits().getTotalHits());
			System.out.println(nbHits);
			aggregationQuery = client.prepareSearch(esIndexEst).setQuery(boolQuery).setTypes(esTypeEst)
					.addAggregation(AggregationBuilders.terms("distinct_" + elsticDbColumn)
							.field(elsticDbColumn + ".keyword").size(nbHits));
		}

		SearchResponse response = null;
		List<DropOption> listOfDropOption = getData(elsticDbColumn, aggregationQuery, response);

		//end_time = System.currentTimeMillis();
		//timeTaken = end_time - start_time;
		//System.out.println("Stat time: " + start_time + "----end time: " + end_time);
		//System.out.println("Time taken to assign aggregation to obj : " + timeTaken);
		return listOfDropOption;
	}

	private List<DropOption> getData(String elsticDbColumn, SearchRequestBuilder aggregationQuery,
			SearchResponse response) {
		//long start_time;
		List<DropOption> listOfDropOption;
			try {
				response = aggregationQuery.execute().get();
			} catch (InterruptedException | ExecutionException e) {
				logger.error("An error occurred during retrieval of distinct dropdown list" + e);
			}
			//end_time = System.currentTimeMillis();
			//long timeTaken = end_time - start_time;
			//System.out.println("Stat time: " + start_time + "----end time: " + end_time);
			//System.out.println("Time taken to fetch aggregation : " + timeTaken);

			//start_time = System.currentTimeMillis();

			Terms terms = response.getAggregations().get("distinct_" + elsticDbColumn);
			listOfDropOption = new ArrayList();
			terms.getBuckets().forEach(entry -> {
				DropOption dropOption = new DropOption();
				dropOption.setName((String) entry.getKey());
				dropOption.setValue((String) entry.getKeyAsString());
				listOfDropOption.add(dropOption);
			});
		
		return listOfDropOption;
	}

	private void createDynamicQuery(String elsticDbColumn, BoolQueryBuilder boolQuery, DropDownRequest dropDownRequest) {
		boolQuery.must(QueryBuilders.existsQuery(elsticDbColumn));
		if(dropDownRequest.getText()!=null && !dropDownRequest.getText().isEmpty())
			likeOperation(elsticDbColumn, dropDownRequest.getText(), boolQuery);
		if(dropDownRequest.getCountryObject()!=null) {
			boolQuery.filter(QueryBuilders.termsQuery(dropDownRequest.getCountryObject().getElasticDbColumn()+".keyword", dropDownRequest.getCountryObject().getCountryNameList()));
		}
	}
	
	
	private void likeOperation(String elasticDbName, String value, BoolQueryBuilder boolQuery){
		QueryBuilder qb;
		 if(value.matches("[0-9]+") || value.matches("[a-zA-Z0-9]*") || (!value.contains(" ") && value.matches("^[A-Za-z, ]++$"))){
			 qb = QueryBuilders.queryStringQuery("*"+ value + "*")
       			.field(elasticDbName).splitOnWhitespace(false);
		 }else{
			 qb = QueryBuilders.queryStringQuery("\"*"+ value + "*\"")
             		.field(elasticDbName).splitOnWhitespace(false);
		 }
		 boolQuery.must(qb);
	}
}
