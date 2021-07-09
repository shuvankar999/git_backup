package com.tip.asset.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.asset.model.ArrayOfDynamicPosVal;
import com.tip.asset.model.DynamicPosVal;
import com.tip.asset.service.DynamicPosValService;
import com.tip.elastic.util.ElasticUtil;

@Service
public class DynamicPosValServiceImpl implements DynamicPosValService {

	@Autowired
	private ElasticUtil elasticUtil;

	@Value("${elasticsearch.host}")
	private String esHost;

	@Value("${elasticsearch.port}")
	private int esPort;

	@Value("${elasticsearch.clustername}")
	private String esClusterName;

	@Value("${elasticsearch.index_dy_pos_val}")
	private String esIndex;

	@Value("${elasticsearch.type_dy_pos_val}")
	private String esType;

	@Override
	public ArrayOfDynamicPosVal getDynamicPosVals(Map<String, String> fields) throws UnknownHostException {

		TransportClient client = elasticUtil.getElasticSearchClient();

		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : fields.entrySet()) {
			boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
		}
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		ArrayOfDynamicPosVal arrayOfDynamicPosVal = new ArrayOfDynamicPosVal();
		do {
			for (SearchHit hit : response.getHits().getHits()) {
				DynamicPosVal dynamicPosVal = setData(hit);
				arrayOfDynamicPosVal.getDynamicPosVals().add(dynamicPosVal);
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
			arrayOfDynamicPosVal.setCount(response.getHits().getTotalHits());
		} while (response.getHits().getHits().length != 0);
		return arrayOfDynamicPosVal;
	}

	@Override
	public Map<String, List<String>> getAllDynamicPosVals(List<Map<String, String>> fielsList)
			throws UnknownHostException {

		TransportClient client = elasticUtil.getElasticSearchClient();

		BoolQueryBuilder outerBoolQuery = new BoolQueryBuilder();
		for (Map<String, String> fields : fielsList) {
			BoolQueryBuilder boolQuery = new BoolQueryBuilder();
			for (Map.Entry<String, String> entry : fields.entrySet()) {
				boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
			}
			outerBoolQuery.should(boolQuery);
		}
		outerBoolQuery.minimumNumberShouldMatch(1);
		Map<String, List<String>> mapDynamicPosVal = new HashMap<>();
		SearchResponse response = client.prepareSearch(esIndex).setQuery(outerBoolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		do {
			for (SearchHit hit : response.getHits().getHits()) {
				Integer specGrpId = (Integer) hit.getSource().get("specgr_id");
				Integer specItmSeq = (Integer) hit.getSource().get("specitm_seq");
				if (mapDynamicPosVal.containsKey(specGrpId + "@@@" + specItmSeq)) {
					mapDynamicPosVal.get(specGrpId + "@@@" + specItmSeq)
							.add((String) hit.getSource().get("specval_descr"));
				} else {
					List<String> desc = new ArrayList<>();
					desc.add((String) hit.getSource().get("specval_descr"));
					mapDynamicPosVal.put(specGrpId + "@@@" + specItmSeq, desc);
				}
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
		} while (response.getHits().getHits().length != 0);
		return mapDynamicPosVal;
	}

	private DynamicPosVal setData(SearchHit hit) {
		DynamicPosVal dynamicPosVal = new DynamicPosVal();
		dynamicPosVal.setSpecgrId((Integer) hit.getSource().get("specgr_id"));
		dynamicPosVal.setSpecitmSeq((Integer) hit.getSource().get("specitm_seq"));
		dynamicPosVal.setSpecvalSeq((Integer) hit.getSource().get("specval_seq"));
		dynamicPosVal.setSpecvalDescr((String) hit.getSource().get("specval_descr"));
		dynamicPosVal.setSpecvalShortcode((String) hit.getSource().get("specval_shortcode"));
		return dynamicPosVal;
	}
}