package com.tip.unitspec.serviceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.elastic.util.ElasticUtil;
import com.tip.unitspec.model.ArrayOfUnitSpecData;
import com.tip.unitspec.model.UnitSpecData;
import com.tip.unitspec.service.UnitSpecDataService;


@Service
public class UnitSpecDataServiceImpl implements UnitSpecDataService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;
    
    @Value("${elasticsearch.index_unit_spec}")
    private String esIndex;
    
    @Value("${elasticsearch.type_unit_spec}")
    private String esType;
    
    public ArrayOfUnitSpecData getUnitData(HashMap<String, String> fields) throws UnknownHostException {
        
    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();        
		
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfUnitSpecData arrayOfUnitsData = new ArrayOfUnitSpecData();        
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	UnitSpecData unitsData = setData(hit);
                arrayOfUnitsData.getUnitSpecDataItem().add(unitsData);
            }

            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        
        return arrayOfUnitsData;
    }
    
    public Long getUnitSpecDataCount(HashMap<String, String> fields) throws UnknownHostException {
    	Long count = 0L;
    	TransportClient client = elasticUtil.getElasticSearchClient();
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
        BoolQueryBuilder boolQuery = new BoolQueryBuilder(); 
		
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        count = response.getHits().getTotalHits();
       return count;
    }
    
    public ArrayOfUnitSpecData unitDataSpecTextSearch(String text) throws UnknownHostException {
    	String allField = "_all";
    	TransportClient client = elasticUtil.getElasticSearchClient();
        //MultiMatchQueryBuilder mmqb = QueryBuilders.multiMatchQuery(text, allField);	
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(allField, text);
        SearchResponse response = client.prepareSearch(esIndex).setQuery(queryBuilder).setTypes(esType).setScroll(new TimeValue(60000)).get();
        ArrayOfUnitSpecData arrayOfUnitsData = new ArrayOfUnitSpecData();
        do {
            for (SearchHit hit : response.getHits().getHits()) {
            	UnitSpecData unitsData = setData(hit);
                arrayOfUnitsData.getUnitSpecDataItem().add(unitsData);
            }

            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
            arrayOfUnitsData.setCount(response.getHits().getTotalHits());
        } while (response.getHits().getHits().length != 0);
        return arrayOfUnitsData;
    }

	private UnitSpecData setData(SearchHit hit) {
		UnitSpecData unitsData = new UnitSpecData();
        unitsData.setUnitCatgrpCode((String) hit.getSource().get("unit_catgrp_code"));
        unitsData.setUnitTsp((String) hit.getSource().get("unit_tsp"));
        unitsData.setUnitModdate((String) hit.getSource().get("maint_date"));
        unitsData.setUnitSoldDate((String) hit.getSource().get("unit_sold_date"));
        unitsData.setInsrcCompanyNr((String) hit.getSource().get("insrc_company_nr"));
        unitsData.setUnitModelYear((Integer) hit.getSource().get("unit_model_year"));
        unitsData.setUnitAvailableForSaleInd((String) hit.getSource().get("unit_available_for_sale_ind"));
        unitsData.setUnitSerialNr((String) hit.getSource().get("unit_serial_nr"));
        unitsData.setUnitLicenceCountryCd((String) hit.getSource().get("unit_licence_country_cd"));
        unitsData.setPoolGroupNr((String) hit.getSource().get("pool_group_nr"));
        unitsData.setUnitCd((String) hit.getSource().get("unit_cd"));
        unitsData.setUnitCostLastRefrnc((String) hit.getSource().get("unit_cost_last_refrnc"));
        unitsData.setUnitCategoryCd((String) hit.getSource().get("unit_category_cd"));
        unitsData.setUnitCatgrpOptionals((String) hit.getSource().get("unit_catgrp_optionals"));
        unitsData.setUnitBuilt((String) hit.getSource().get("unit_built"));
        unitsData.setUnitSpecStatus((String) hit.getSource().get("unit_spec_status"));
        unitsData.setUnitCustomerRefrnc((String) hit.getSource().get("unit_customer_refrnc"));
        unitsData.setUnitRegistr((String) hit.getSource().get("unit_registr"));
        unitsData.setUnitGroupCd((String) hit.getSource().get("unit_group_cd"));
        unitsData.setPoolUnitOwner((String) hit.getSource().get("pool_unit_owner"));
        unitsData.setRegstnDate((String) hit.getSource().get("regstn_date"));
        unitsData.setUnitManufacturer((String) hit.getSource().get("unit_manufacturer"));
        unitsData.setUnitComment((String) hit.getSource().get("unit_comment"));
        unitsData.setUnitLienCd((String) hit.getSource().get("unit_lien_cd"));
        unitsData.setUnitNewUsedLeasedInd((String) hit.getSource().get("unit_new_used_leased_ind"));
        unitsData.setUnitLicenceNr((String) hit.getSource().get("unit_licence_nr"));
        //unitsData.setMa((String)searchHits[i].getSource().get("maint_user"));
        unitsData.setUnitOperationalStatus((String) hit.getSource().get("unit_operational_status"));
        unitsData.setUnitRefurbComment((String) hit.getSource().get("unit_refurb_comment"));
        unitsData.setUnitNr((Integer) hit.getSource().get("unit_nr"));
        unitsData.setUnitAquiredCompany((String) hit.getSource().get("unit_aquired_company"));
        unitsData.setReeferMaintContract((String) hit.getSource().get("reefer_maint_contract"));
        unitsData.setCatgrId((Integer) hit.getSource().get("catgr_id"));
        unitsData.setUnitAquiredRefrnc((String) hit.getSource().get("unit_aquired_refrnc"));
        unitsData.setUnitModdate((String) hit.getSource().get("unit_moddate"));
        unitsData.setUnitDesc((String) hit.getSource().get("unit_desc"));
		return unitsData;
	}
	
	@Override
    public Long unitDataSpecTextSearchCount(String text) throws UnknownHostException {
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

