package com.tip.supplier.service.impl;

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

import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.Filter;
import com.tip.supplier.model.ArrayOfSupplierData;
import com.tip.supplier.model.SupplierSearchResponse;
import com.tip.supplier.service.SupplierDynamicFiltersService;


@Service
public class SupplierDynamicFiltersServiceImpl implements SupplierDynamicFiltersService {

	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.index_supplier}")
	private String esIndex;

	@Value("${elasticsearch.type_supplier}")
	private String esType;

	static final Logger logger = LoggerFactory.getLogger(SupplierDynamicFiltersServiceImpl.class);

	@Override
	public Long getTotalCountOfTextSearch(DynamicFilterRequest dynamicFilterRequest) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();

		Long count = 0L;
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();

		createQuery(dynamicFilterRequest, boolQuery);
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
	public ArrayOfSupplierData getPaginatedEstimationData(DynamicFilterRequest dynamicFilterRequest, int from,
			int size) throws UnknownHostException {

		TransportClient client = elasticUtil.getElasticSearchClient();
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		createQuery(dynamicFilterRequest, boolQuery);

		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setFrom(from)
				.setSize(size).setExplain(true).execute().actionGet();
		ArrayOfSupplierData arrayOfSupplierData = new ArrayOfSupplierData();
		for (SearchHit hit : response.getHits().getHits()) {
			SupplierSearchResponse supplierSearchResponse = setData(hit);
			arrayOfSupplierData.getSupplierDataItem().add(supplierSearchResponse);
		}
		arrayOfSupplierData.setCount(Long.valueOf(response.getHits().getHits().length));
		return arrayOfSupplierData;
	}

	private SupplierSearchResponse setData(SearchHit hit) {
		SupplierSearchResponse supplierSearchResponse = new SupplierSearchResponse();
		supplierSearchResponse.setSupplierId((Integer) hit.getSource().get("vendor_id"));
		supplierSearchResponse.setSupplierName((String) hit.getSource().get("vendor_name"));
		supplierSearchResponse.setSupplierPriority((String) hit.getSource().get("vendor_priority"));
		supplierSearchResponse.setSupplierType((String) hit.getSource().get("vendor_category"));
		supplierSearchResponse.setVendorStatus((String) hit.getSource().get("vendor_status_desc"));
		supplierSearchResponse.setCity((String) hit.getSource().get("vendor_city"));
		supplierSearchResponse.setCountryName((String) hit.getSource().get("country_name"));
		supplierSearchResponse.setVatRegNr((String) hit.getSource().get("vat_registraion_nr"));
		supplierSearchResponse.setCurrencyCd((String) hit.getSource().get("currency_cd"));
		return supplierSearchResponse;
	}

	private void createQuery(DynamicFilterRequest dynamicFilterRequest, BoolQueryBuilder boolQuery) {
		QueryBuilder qb;
		for (Filter filter : dynamicFilterRequest.getFilter()) {
			if ("AND".equals(filter.getCondition())) {
				if ("equals".equals(filter.getOperation())) {
					boolQuery.must(QueryBuilders.matchQuery(filter.getElasticDbField().toLowerCase(), filter.getValue()));
				} else if ("range".equals(filter.getOperation())) {
					boolQuery.must(QueryBuilders.rangeQuery(filter.getElasticDbField().toLowerCase()).from(filter.getRangeGt())
							.to(filter.getRangeLt()).includeLower(filter.isIncludeLower())
							.includeUpper(filter.isIncludeUpper()));
				} else if ("multiFilter".equals(filter.getOperation())) {
					boolQuery.filter(
							QueryBuilders.termsQuery(filter.getElasticDbField().toLowerCase() + ".keyword", filter.getValueList()));

				} else if ("searchtext".equals(filter.getOperation())) {

					if (filter.getValue().matches("[0-9]+")) {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("vendor_id_str")
								.field("vendor_name");
					} else if (filter.getValue().matches("^[A-Za-z, ]++$")) {
						qb = QueryBuilders.queryStringQuery("\"*" + filter.getValue() + "*\"")
								.field("vendor_id_str")
								.field("vendor_name");
					} else if (filter.getValue().matches("[a-zA-Z0-9]*")) {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("vendor_id_str")
								.field("vendor_name");
					} else {
						qb = QueryBuilders.queryStringQuery("*" + filter.getValue() + "*")
								.field("vendor_id_str")
								.field("vendor_name");
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