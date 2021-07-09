package com.tip.estimationfilter.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.estimationfilter.model.ArrayOfEstimationData;
import com.tip.estimationfilter.model.DynamicFilterRequest;
import com.tip.estimationfilter.model.EstimationSearchResponse;
import com.tip.estimationfilter.model.Filter;
import com.tip.estimationfilter.service.EstimationDynamicFiltersService;
import com.tip.util.ElasticUtil;

@Service
public class EstimationDynamicFiltersServiceImpl implements EstimationDynamicFiltersService {

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

	static final Logger logger = LoggerFactory.getLogger(EstimationDynamicFiltersServiceImpl.class);

	@Override
	public Long getTotalCountOfTextSearch(DynamicFilterRequest dynamicFilterRequest) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();

		Long count ;
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();

		createQueryEstimationIndex(dynamicFilterRequest, boolQuery);
		
		logger.info(boolQuery.toString());
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
				.actionGet();
		count = response.getHits().getTotalHits();
		logger.info(count.toString());
		return count;
	}

	@Override
	public ArrayOfEstimationData getPaginatedEstimationData(DynamicFilterRequest dynamicFilterRequest, int from,
			int size) throws UnknownHostException {

		TransportClient client = elasticUtil.getElasticSearchClient();

		client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		createQueryEstimationIndex(dynamicFilterRequest, boolQuery);
		logger.info(boolQuery.toString());
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setFrom(from)
				.setSize(size).setExplain(true).execute().actionGet();
		ArrayOfEstimationData arrayOfEstimationData = new ArrayOfEstimationData();
		for (SearchHit hit : response.getHits().getHits()) {
			EstimationSearchResponse estimationSearchResponse = setData(hit);
			arrayOfEstimationData.getListOfEstimationData().add(estimationSearchResponse);
		}
		arrayOfEstimationData.setCount(response.getHits().getHits().length);
		return arrayOfEstimationData;
	}

	private void createQueryEstimationIndex(DynamicFilterRequest dynamicFilterRequest, BoolQueryBuilder boolQuery) {
		QueryBuilder qb;
		for (Filter filter : dynamicFilterRequest.getFilter()) {
			if ("AND".equals(filter.getCondition())) {
				if ("equals".equals(filter.getOperation())) {
					boolQuery.must(QueryBuilders.matchQuery(filter.getElasticDbField(), filter.getValue()).operator(Operator.AND));
				} else if ("range".equals(filter.getOperation())) {
					boolQuery.must(QueryBuilders.rangeQuery(filter.getElasticDbField()).from(filter.getRangeGt())
							.to(filter.getRangeLt()).includeLower(filter.isIncludeLower())
							.includeUpper(filter.isIncludeUpper()));
				} else if ("multiFilter".equals(filter.getOperation())) {
					if(filter.getElasticDbField().equalsIgnoreCase("branch")) {
						BoolQueryBuilder innerQuery = new BoolQueryBuilder();
						innerQuery.should(QueryBuilders.termsQuery(filter.getElasticDbField() + ".keyword", filter.getValueList()));
						innerQuery.should(QueryBuilders.termsQuery("inspect_loc.keyword", filter.getValueList()));
						boolQuery.filter(innerQuery);
					}else {
						boolQuery.filter(
								QueryBuilders.termsQuery(filter.getElasticDbField() + ".keyword", filter.getValueList()));
					}

				} else if ("searchtext".equals(filter.getOperation())) {

					if (filter.getValue().matches("[0-9]+")) {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("estimation_id_str")
								.field("estimation_nr")
								.field("tipassetnrstr");
					} else if (filter.getValue().matches("^[A-Za-z, ]++$")) {
						qb = QueryBuilders.queryStringQuery("\"*" + filter.getValue() + "*\"")
								.field("estimation_id_str")
								.field("estimation_nr")
								.field("tipassetnrstr");
					} else if (filter.getValue().matches("[a-zA-Z0-9]*")) {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("estimation_id_str")
								.field("estimation_nr")
								.field("tipassetnrstr");
					} else {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("estimation_id_str")
								.field("estimation_nr")
								.field("tipassetnrstr");
					}

					boolQuery.must(qb);
				}
			}
		}
	}

	private EstimationSearchResponse setData(SearchHit hit) {
		EstimationSearchResponse estimationSearchResponse = new EstimationSearchResponse();
		estimationSearchResponse.setEstimationNr((String) hit.getSource().get("estimation_nr"));
		estimationSearchResponse.setEstimationId((String) hit.getSource().get("estimation_id_str"));
		estimationSearchResponse.setAssetNr((String) hit.getSource().get("tipassetnrstr"));
		estimationSearchResponse.setEstimationDate((String) hit.getSource().get("estimate_date"));
		estimationSearchResponse.setCustomerNr((String) hit.getSource().get("customer_nr_str"));
		estimationSearchResponse.setCustomerName((String) hit.getSource().get("customer_name"));
		estimationSearchResponse.setEstimationTitle((String) hit.getSource().get("estimation_title"));
		estimationSearchResponse.setEstimationBy((String) hit.getSource().get("estimated_by"));
		estimationSearchResponse.setEstimationStatus((String) hit.getSource().get("estimation_status"));
		estimationSearchResponse.setCountry((String) hit.getSource().get("physical_country_name"));
		estimationSearchResponse.setBranch((String) hit.getSource().get("branch"));
		estimationSearchResponse.setCustAssetNr((String) hit.getSource().get("cust_asset_refnr"));
		estimationSearchResponse.setTotalPrice(hit.getSource().get("totalprice"));
		estimationSearchResponse.setContributionValue(hit.getSource().get("contributionvalue"));
		estimationSearchResponse.setLocation((String)hit.getSource().get("inspect_loc"));

		return estimationSearchResponse;
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