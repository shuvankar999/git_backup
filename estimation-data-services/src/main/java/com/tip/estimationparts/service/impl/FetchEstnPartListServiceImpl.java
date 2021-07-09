package com.tip.estimationparts.service.impl;

import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
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

import com.tip.estimationparts.model.PartListResponse;
import com.tip.estimationparts.model.PartsObjectList;
import com.tip.estimationparts.model.PartsRequest;
import com.tip.estimationparts.model.SearchText;
import com.tip.estimationparts.service.FetchEstnPartListService;
import com.tip.util.ElasticUtil;

@Service
public class FetchEstnPartListServiceImpl implements FetchEstnPartListService{
	
	@Autowired
	private ElasticUtil elasticUtil;

	@Value("${elasticsearch.index_estimationparts}")
	private String esIndex;

	@Value("${elasticsearch.type_estimationparts}")
	private String esType;

	static final Logger logger = LoggerFactory.getLogger(FetchEstnPartListServiceImpl.class);

	@Override
	public PartListResponse getFilterPartsList(PartsRequest partsRequest) throws UnknownHostException {
		PartListResponse partListResponse=new PartListResponse();
		TransportClient client = elasticUtil.getElasticSearchClient();
		 BoolQueryBuilder boolQuery = new BoolQueryBuilder();
         boolQuery.must(QueryBuilders.termQuery("companynr",partsRequest.getCompanynr()));
         
         for(SearchText searchText:partsRequest.getSearchTextList()){
        	 QueryBuilder qb;
        	 if (searchText.getValue().contains("[0-9]+")) {
					qb = QueryBuilders.queryStringQuery("*" + searchText.getValue() + "*")
							.field("partsdescription")
							.field("supplierpartnr");
				} else if (searchText.getValue().contains("^[A-Za-z, ]++$")) {
					qb = QueryBuilders.queryStringQuery("\"*" + searchText.getValue() + "*\"")
							.field("partsdescription")
							.field("supplierpartnr");
				} else if (searchText.getValue().contains("[a-zA-Z0-9]*")) {
					qb = QueryBuilders.queryStringQuery("*" + searchText.getValue() + "*")
							.field("partsdescription")
							.field("supplierpartnr");
				} else {
					qb = QueryBuilders.queryStringQuery("*" + searchText.getValue() + "*")
							.field("partsdescription")
							.field("supplierpartnr");
				}

				boolQuery.must(qb);
			}
	
         
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		 do {
	            for (SearchHit hit : response.getHits().getHits()) {
	            	PartsObjectList partsObjectList=setData(hit);
	            	partListResponse.getEstnPartsList().add(partsObjectList);
	           }
	            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
	        } while (response.getHits().getHits().length != 0);
		
		 partListResponse.setCount(response.getHits().getTotalHits());
		 return partListResponse;
	}

	
	private PartsObjectList setData(SearchHit hit) {
	
		PartsObjectList partsObjectList=new PartsObjectList();
		partsObjectList.setPartsdescription((String) hit.getSource().get("partsdescription"));
		partsObjectList.setSupplierpartnr((String) hit.getSource().get("supplierpartnr"));
		partsObjectList.setSupplierid((Integer) hit.getSource().get("supplierid"));
		partsObjectList.setCostprice((Double) hit.getSource().get("costprice"));
		partsObjectList.setOelistprice(Double.valueOf(hit.getSource().get("oelistprice").toString()));
	//	partsObjectList.setOelistprice((Double)hit.getSource().get("oelistprice"));		
		partsObjectList.setCurrency((String) hit.getSource().get("currency"));
		partsObjectList.setTippartdna((String) hit.getSource().get("tippartdna"));
		return partsObjectList;
	}
		
}




