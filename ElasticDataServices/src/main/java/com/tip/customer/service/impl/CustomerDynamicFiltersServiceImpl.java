package com.tip.customer.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

import com.tip.customer.model.ArrayOfCustomerData;
import com.tip.customer.model.CustomerSearchResponse;
import com.tip.customer.service.CustomerDataService;
import com.tip.customer.service.CustomerDynamicFiltersService;
import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.Filter;


@Service
public class CustomerDynamicFiltersServiceImpl implements CustomerDynamicFiltersService {

	@Autowired
	private ElasticUtil elasticUtil;
	
	@Autowired
	CustomerDataService customerDataService;

	@Value("${elasticsearch.host}")
	private String esHost;

	@Value("${elasticsearch.port}")
	private int esPort;

	@Value("${elasticsearch.clustername}")
	private String esClusterName;

	@Value("${elasticsearch.index_customer}")
	private String esIndex;

	@Value("${elasticsearch.type_customer}")
	private String esType;

	static final Logger logger = LoggerFactory.getLogger(CustomerDynamicFiltersServiceImpl.class);

	@Override
	public Long getTotalCountOfTextSearch(DynamicFilterRequest dynamicFilterRequest) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();

		Long count = 0L;
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();

		createQueryCustomerIndex(dynamicFilterRequest, boolQuery);
		System.out.println(boolQuery.toString());
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
				.actionGet();
		count = response.getHits().getTotalHits();
		System.out.println(count);
		return count;
	}

	@Override
	public ArrayOfCustomerData getPaginatedEstimationData(DynamicFilterRequest dynamicFilterRequest, int from,
			int size) throws UnknownHostException {

		TransportClient client = elasticUtil.getElasticSearchClient();

		client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		createQueryCustomerIndex(dynamicFilterRequest, boolQuery);

		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setFrom(from)
				.setSize(size).setExplain(true).execute().actionGet();
		ArrayOfCustomerData arrayOfCustomerData = new ArrayOfCustomerData();
		for (SearchHit hit : response.getHits().getHits()) {
			CustomerSearchResponse customerSearchResponse = customerDataService.setData(hit);
			arrayOfCustomerData.getCustomerDataItem().add(customerSearchResponse);
		}
		arrayOfCustomerData.setCount(Long.valueOf(response.getHits().getHits().length));
		return arrayOfCustomerData;
	}

	private void createQueryCustomerIndex(DynamicFilterRequest dynamicFilterRequest, BoolQueryBuilder boolQuery) {
		QueryBuilder qb;
		for (Filter filter : dynamicFilterRequest.getFilter()) {
			if ("AND".equals(filter.getCondition())) {
				if ("equals".equals(filter.getOperation())) {
					boolQuery.must(QueryBuilders.matchQuery(filter.getElasticDbField(), filter.getValue()));
				} else if ("range".equals(filter.getOperation())) {
					boolQuery.must(QueryBuilders.rangeQuery(filter.getElasticDbField()).from(filter.getRangeGt())
							.to(filter.getRangeLt()).includeLower(filter.isIncludeLower())
							.includeUpper(filter.isIncludeUpper()));
				} else if ("multiFilter".equals(filter.getOperation())) {
					boolQuery.filter(
							QueryBuilders.termsQuery(filter.getElasticDbField() + ".keyword", filter.getValueList()));

				} else if ("searchtext".equals(filter.getOperation())) {

					if (filter.getValue().matches("[0-9]+")) {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("customer_nr_str")
								.field("customer_name");
					} else if (filter.getValue().matches("^[A-Za-z, ]++$")) {
						qb = QueryBuilders.queryStringQuery("\"*" + filter.getValue() + "*\"")
								.field("customer_nr_str")
								.field("customer_name");
					} else if (filter.getValue().matches("[a-zA-Z0-9]*")) {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("customer_nr_str")
								.field("customer_name");
					} else {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("customer_nr_str")
								.field("customer_name");
					}

					boolQuery.must(qb);
				}
			}
		}
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