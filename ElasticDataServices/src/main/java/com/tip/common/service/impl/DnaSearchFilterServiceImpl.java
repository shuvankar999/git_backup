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

import com.tip.common.model.DnaListObject;
import com.tip.common.model.FilterRequest;
import com.tip.common.model.FilterResponse;
import com.tip.common.service.DnaSearchFilterService;
import com.tip.elastic.util.ElasticUtil;


	@Service
	public class DnaSearchFilterServiceImpl implements DnaSearchFilterService{
		
		@Autowired
		private ElasticUtil elasticUtil;
		
		
		@Value("${elasticsearch.index_dna}")
		private String esIndex;

		@Value("${elasticsearch.type_dna}")
		private String esType;
		
		static final Logger logger = LoggerFactory.getLogger(DnaSearchFilterServiceImpl.class);

		@Override
		public FilterResponse getFilterList(FilterRequest filterRequest) throws UnknownHostException {
			
			FilterResponse filterResponse=new FilterResponse();
			
			TransportClient client = elasticUtil.getElasticSearchClient();
			 BoolQueryBuilder boolQuery = new BoolQueryBuilder();
	        
			 boolQuery.must(QueryBuilders.termQuery("lang_id",filterRequest.getLangId()));
	         
	         for(String searchText : filterRequest.getSearchTextList()){
	        	 QueryBuilder qb;
	        	 if (searchText.contains("[0-9]+")) {
						qb = QueryBuilders.queryStringQuery("*" + searchText + "*")
								.field("group_desc")
								.field("activity_desc")
								.field("subgroup_desc");
								
					} else if (searchText.contains("^[A-Za-z, ]++$")) {
						qb = QueryBuilders.queryStringQuery("\"*" + searchText + "*\"")
								.field("group_desc")
								.field("activity_desc")
								.field("subgroup_desc");
					} else if (searchText.contains("[a-zA-Z0-9]*")) {
						qb = QueryBuilders.queryStringQuery("*" + searchText + "*")
								.field("group_desc")
								.field("activity_desc")
								.field("subgroup_desc");
					} else {
						qb = QueryBuilders.queryStringQuery("*" + searchText + "*")
								.field("group_desc")
								.field("activity_desc")
								.field("subgroup_desc");
					}

					boolQuery.must(qb);
				}
		
	         
			SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
					.setScroll(new TimeValue(60000)).get();
			
			 do {
		            for (SearchHit hit : response.getHits().getHits()) {
		            	DnaListObject dnaListObject=setData(hit);
		            	filterResponse.getDnaListDetails().add(dnaListObject);
		           }
		            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
		        } while (response.getHits().getHits().length != 0);
			
			 filterResponse.setCount(response.getHits().getTotalHits());
			 return filterResponse;
		}

		
		private DnaListObject setData(SearchHit hit) {
		
			DnaListObject dnaListObject=new DnaListObject();
			dnaListObject.setActivity((String) hit.getSource().get("activity"));
			dnaListObject.setActivityDesc((String) hit.getSource().get("activity_desc"));
			dnaListObject.setGroupDesc((String) hit.getSource().get("group_desc"));
			dnaListObject.setLangId((String) hit.getSource().get("lang_id"));
			dnaListObject.setDnaCd((String) hit.getSource().get("dna_cd"));
			dnaListObject.setSubgroupCd((String) hit.getSource().get("subgroup_cd"));
			dnaListObject.setSubgroupDesc((String) hit.getSource().get("subgroup_desc"));
			dnaListObject.setGroupCd((String) hit.getSource().get("group_cd"));
			return dnaListObject;
		}
			
	}

	
	
