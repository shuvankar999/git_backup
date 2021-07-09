package com.tip.common.service.impl;

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

import com.tip.common.model.EmployeeDetailsRequest;
import com.tip.common.model.EmployeeDetailsResponse;
import com.tip.common.model.EmployeeObject;
import com.tip.common.service.EmployeeDetailsService;
import com.tip.elastic.util.ElasticUtil;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	private ElasticUtil elasticUtil;

	@Value("${elasticsearch.index_employee}")
	private String esIndex;

	@Value("${elasticsearch.type_employee}")
	private String esType;

	static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsServiceImpl.class);

	@Override
	public EmployeeDetailsResponse fetchEmployeeDetails(EmployeeDetailsRequest employeeDetailsRequest)
			throws UnknownHostException {
		EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
		TransportClient client = elasticUtil.getElasticSearchClient();
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();

		QueryBuilder qb;

		if (employeeDetailsRequest.getSearchText().contains("[0-9]+")) {
			qb = QueryBuilders.queryStringQuery("*" + employeeDetailsRequest.getSearchText() + "*").field("emplid")
					.field("emplname").field("emplrole");
		} else if (employeeDetailsRequest.getSearchText().contains("^[A-Za-z, ]++$")) {
			qb = QueryBuilders.queryStringQuery("\"*" + employeeDetailsRequest.getSearchText() + "*\"").field("emplid")
					.field("emplname").field("emplrole");
		} else if (employeeDetailsRequest.getSearchText().contains("[a-zA-Z0-9]*")) {
			qb = QueryBuilders.queryStringQuery("*" + employeeDetailsRequest.getSearchText() + "*").field("emplid")
					.field("emplname").field("emplrole");
		} else {
			qb = QueryBuilders.queryStringQuery("*" + employeeDetailsRequest.getSearchText() + "*").field("emplid")
					.field("emplname").field("emplrole");
		}

		boolQuery.must(qb);

		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		do {
			for (SearchHit hit : response.getHits().getHits()) {
				EmployeeObject employeeObject = fetchEmployeeData(hit);
				employeeDetailsResponse.getEmployeeDetailsList().add(employeeObject);
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
		} while (response.getHits().getHits().length != 0);

		employeeDetailsResponse.setCount(response.getHits().getTotalHits());
		return employeeDetailsResponse;
	}

	private EmployeeObject fetchEmployeeData(SearchHit hit) {

		EmployeeObject employeeObject = new EmployeeObject();
		employeeObject.setEmplid((String) hit.getSource().get("emplid"));
		employeeObject.setEmplname((String) hit.getSource().get("emplname"));
		employeeObject.setEmplrole((String) hit.getSource().get("emplrole"));

		return employeeObject;
	}

}
