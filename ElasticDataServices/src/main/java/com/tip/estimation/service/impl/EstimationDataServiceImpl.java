package com.tip.estimation.service.impl;

import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.elastic.util.ElasticUtil;
import com.tip.estimation.service.EstimationDataService;

@Service
public class EstimationDataServiceImpl implements EstimationDataService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
	private String esHost;

	@Value("${elasticsearch.port}")
	private int esPort;

	@Value("${elasticsearch.clustername}")
	private String esClusterName;

	@Value("${elasticsearch.index_estimation}")
	private String esIndex;

	@Value("${elasticsearch.type_estimation}")
	private String esType;

	static final Logger logger = LoggerFactory.getLogger(EstimationDataServiceImpl.class);

	@Override
	public Long estimationDataTextSearchCount(String text) throws UnknownHostException {
		Long count = 0L;
		TransportClient client = elasticUtil.getElasticSearchClient();
		QueryBuilder qb;
		if (text.matches("[0-9]+")) {
			qb = QueryBuilders.queryStringQuery("*" + text + "*")
					.field("estimation_id_str")
					.field("estimation_nr");
		} else if (text.matches("^[A-Za-z, ]++$")) {
			qb = QueryBuilders.queryStringQuery("\"*" + text + "*\"")
					.field("estimation_id_str")
					.field("estimation_nr");
		} else if (text.matches("[a-zA-Z0-9]*")) {
			qb = QueryBuilders.queryStringQuery("*" + text + "*")
					.field("estimation_id_str")
					.field("estimation_nr");
		} else {
			qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
					.field("estimation_id_str")
					.field("estimation_nr");
		}
		try {
			SearchResponse response = client.prepareSearch(esIndex).setQuery(qb).setTypes(esType)
					.setScroll(new TimeValue(60000)).get();
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
			count = response.getHits().getTotalHits();
			
		} catch (IndexNotFoundException e) {
			logger.error("An error occurred while fetching data from elasticsearch : ( index name-"+ esIndex +")"+e);
		}
		
		return count;
		
		
	}
	
	public boolean checkIntValue(String val) {
		boolean flag = true;
		try {
			Integer.parseInt(val);
		} catch (NumberFormatException e) {
			flag = false;
		}
		return flag;
	}
}
