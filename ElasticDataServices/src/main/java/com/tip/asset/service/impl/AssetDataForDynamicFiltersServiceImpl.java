package com.tip.asset.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
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

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.AssetSearchResponse;
import com.tip.asset.service.AssetDataForDynamicFiltersService;
import com.tip.asset.util.AssetConstants;
import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DynamicFilterAssetSpec;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.Filter;
import com.tip.equipmentdetails.model.FilterAssetSpec;
import com.tip.equipmentdetails.model.ListOfUnit;

@Service
public class AssetDataForDynamicFiltersServiceImpl implements AssetDataForDynamicFiltersService {
	
	@Autowired
	private ElasticUtil elasticUtil;
    
    @Value("${elasticsearch.index_asset}")
    private String esIndex;
    
    @Value("${elasticsearch.index_assetspec}")
    private String esIndexAssetSpec;
    
    @Value("${elasticsearch.index_tp_asset}")
    private String esIndexTpAsset;
    
    @Value("${elasticsearch.type_asset}")
    private String esType;
    
    @Value("${elasticsearch.type_assetspec}")
    private String esTypeAssetSpec;
    
    @Value("${elasticsearch.type_tp_asset}")
    private String esTypeTpAsset;
    
    
    
    
    static final Logger logger = LoggerFactory.getLogger(AssetDataForDynamicFiltersServiceImpl.class);
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<Integer, AssetSearchResponse> dynamicFilterAsset(DynamicFilterRequest dynamicFilterRequest, DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit, ArrayOfAssetData arrayOfAssetData) throws UnknownHostException {
    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        Map<Integer, AssetSearchResponse> assetDataMap = new HashMap();
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        
        createQueryAssetIndex(dynamicFilterRequest, boolQuery);
        System.out.println(boolQuery.toString());
        SearchResponse response = client.prepareSearch(esIndex,esIndexTpAsset).setQuery(boolQuery).setTypes(esType,esTypeTpAsset).setScroll(new TimeValue(60000)).get();
        
        try{
        	List<String> units = new ArrayList();
        	long nbHits = response.getHits().getTotalHits();
        	System.out.println("***********Total Hits from asset, tpasset index*******"+nbHits);
			do {
					response.getHits().forEach(hit -> {
					AssetSearchResponse assetSearchResponse = setData(hit);
					units.add(Integer.toString((Integer) hit.getSource().get("unit_nr")));
					arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
					assetDataMap.put((Integer) hit.getSource().get("unit_nr"), assetSearchResponse);
				}
        	);
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
        
        listOfUnit.setUnits(units);
        
        arrayOfAssetData.setCount(response.getHits().getTotalHits());
        }catch(Exception e){
        	logger.error("Error occurred: "+e);
        	e.printStackTrace();
        }
        return assetDataMap;
    }

    @Override
    public Map<Integer, AssetSearchResponse> dynamicFilterAsset(DynamicFilterRequest dynamicFilterRequest, DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit, ArrayOfAssetData arrayOfAssetData, Integer from, Integer size) throws UnknownHostException {
    	TransportClient client = elasticUtil.getElasticSearchClient();
    	
        Map<Integer, AssetSearchResponse> assetDataMap = new HashMap();
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        
        createQueryAssetIndex(dynamicFilterRequest, boolQuery);
        System.out.println(boolQuery.toString());        
        SearchResponse response = client.prepareSearch(esIndex,esIndexTpAsset).setQuery(boolQuery).setTypes(esType,esTypeTpAsset).setFrom(from)
				.setSize(size).setExplain(true).execute().actionGet();
        
        try{
        	List<String> units = new ArrayList();
        	long nbHits = response.getHits().getTotalHits();
        	System.out.println("***********Total Hits from asset, tpasset index pagination*******"+nbHits);
    		response.getHits().forEach(hit -> {
    			AssetSearchResponse assetSearchResponse = setData(hit);
    			units.add(Integer.toString((Integer) hit.getSource().get("unit_nr")));
    			arrayOfAssetData.getAssetDataItem().add(assetSearchResponse);
    			assetDataMap.put((Integer) hit.getSource().get("unit_nr"), assetSearchResponse);
    		});
            listOfUnit.setUnits(units);
            arrayOfAssetData.setCount(nbHits);
        }catch(Exception e){
        	logger.error("Error occurred: "+e);
        	e.printStackTrace();
        }
        return assetDataMap;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<AssetSearchResponse> dynamicFilterAssetSpecs(DynamicFilterRequest dynamicFilterRequest, DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit, Map<Integer, AssetSearchResponse> assetDataMap) throws UnknownHostException {
        
    	TransportClient client = elasticUtil.getElasticSearchClient();
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();         
        createQueryAsstspecsIndex(dynamicFilterAssetSpec, listOfUnit, boolQuery);
        SearchResponse response = client.prepareSearch(esIndexAssetSpec).setQuery(boolQuery).setTypes(esTypeAssetSpec).setScroll(new TimeValue(60000)).get();
        List<AssetSearchResponse> listOfAssetSearch = new ArrayList();
        System.out.println(boolQuery);
        try{
        	long nbHits = response.getHits().getTotalHits();
        	System.out.println("***********Total Hits from assetspecs index*******"+nbHits);
			do {
				response.getHits().forEach(hit -> {
					if (nbHits > 0) {
						AssetSearchResponse assetSearchResponse = assetDataMap
								.get((Integer) hit.getSource().get("unit_nr"));
						listOfAssetSearch.add(assetSearchResponse);
					}
				}
        	);
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);

        }catch(Exception e){
        	logger.error("Error occurred: "+e);
        	e.printStackTrace();
        }
        return listOfAssetSearch;
    }


	private void createQueryAssetIndex(DynamicFilterRequest dynamicFilterRequest, BoolQueryBuilder boolQuery) {
		QueryBuilder qb;
		for (Filter filter : dynamicFilterRequest.getFilter()){
        	if("AND".equals(filter.getCondition()))
        	{
        		
        		if("equals".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders.matchQuery(filter.getElasticDbField(), filter.getValue()).operator(Operator.AND));
        		} else if("range".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders
        					.rangeQuery(filter.getElasticDbField())
        					.from(filter.getRangeGt())
        					.to(filter.getRangeLt())
        					.includeLower(filter.isIncludeLower())
                            .includeUpper(filter.isIncludeUpper()));
        		} else if("like".equals(filter.getOperation())) {
        			likeOperation(filter.getElasticDbField(),filter.getValue(),boolQuery);
        		} else if("multiFilter".equals(filter.getOperation())) {
        			BoolQueryBuilder innerBoolQuery = new BoolQueryBuilder();
        			if(filter.getElasticDbField().equalsIgnoreCase("unit_physical_branch_nr"))
        				boolQuery.filter(QueryBuilders.termsQuery(filter.getElasticDbField(), filter.getValueList()));
        			else if(filter.getElasticDbField().equalsIgnoreCase("unit_operational_status_desc")) {
        				if(filter.getValueList().size()==0) {
        					boolQuery.mustNot(QueryBuilders.termsQuery(filter.getElasticDbField()+".keyword", Arrays.asList("Sold","Non Sale Disposal")));
        				}else {
        					boolQuery.filter(QueryBuilders.termsQuery(filter.getElasticDbField()+".keyword", filter.getValueList()));
        				}
        			}else
        				boolQuery.filter(QueryBuilders.termsQuery(filter.getElasticDbField()+".keyword", filter.getValueList()));
        			
        		} else if("searchtext".equals(filter.getOperation())) {

        			 if(filter.getValue().matches("[0-9]+")){
        				 qb = QueryBuilders.queryStringQuery("*"+ filter.getValue() + "*")
                     			.field("unit_nr")
                         		.field("unit_serial_nr")
                         		.field("unit_licence_nr")
                         		.field("unit_cd")
                         		.field("po_number")
                         		.field("unit_model_year_text")
                         		.field("unit_customer_refrnc").splitOnWhitespace(false);
        			 }else if(filter.getValue().matches("^[A-Za-z, ]++$")){
        				 qb = QueryBuilders.queryStringQuery("\"*"+ filter.getValue() + "*\"")
                     			.field("unit_serial_nr")
                         		.field("unit_licence_nr")
                         		.field("unit_cd")
                         		.field("po_number")
                         		.field("unit_customer_refrnc").splitOnWhitespace(false);
        			 }else if(filter.getValue().matches("[a-zA-Z0-9]*")){
        				 qb = QueryBuilders.queryStringQuery("*"+ filter.getValue() + "*")
        						.field("unit_serial_nr")
                          		.field("unit_licence_nr")
                          		.field("unit_cd")
                          		.field("po_number")
                          		.field("unit_customer_refrnc").splitOnWhitespace(false);
        			 }else{
        				 qb = QueryBuilders.queryStringQuery("\"*"+ filter.getValue() + "*\"")
         						.field("unit_serial_nr")
                           		.field("unit_licence_nr")
                           		.field("unit_cd")
                           		.field("po_number")
                           		.field("bcheck_service_date")
                           		.field("reefer_service_date")
                           		.field("unit_customer_refrnc").splitOnWhitespace(false);
        			 }
        			
            		boolQuery.must(qb);
        		}      		
        	}
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
    
	private void createQueryAsstspecsIndex(DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit,
			BoolQueryBuilder boolQuery) {
		for (FilterAssetSpec filter : dynamicFilterAssetSpec.getFilter()){
        	if("AND".equals(filter.getCondition()))
        	{
        		if("multiFilter".equals(filter.getOperation())) {
        			BoolQueryBuilder innerBoolQuery = new BoolQueryBuilder();
        			for(String value : filter.getValueList()){
        				innerBoolQuery.must(QueryBuilders.matchQuery(filter.getElasticDbField(), value));
        			}
                    boolQuery.must(innerBoolQuery);
        		}else if("equalsspecs".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders.termQuery("unitsp_value", filter.getValue()));
        			boolQuery.must(QueryBuilders.termQuery("specgr_id", filter.getFilterCd()));
        			boolQuery.must(QueryBuilders.termQuery("specitm_seq", filter.getLabelCd()));
        		}else if("likespecs".equals(filter.getOperation())) {
        			//boolQuery.must(QueryBuilders.matchPhraseQuery("unitsp_value", filter.getValue()));
        			likeOperation("unitsp_value", filter.getValue(),boolQuery);
        			boolQuery.must(QueryBuilders.termQuery("specgr_id", filter.getFilterCd()));
        			boolQuery.must(QueryBuilders.termQuery("specitm_seq", filter.getLabelCd()));
        		}else if("rangespecs".equals(filter.getOperation())) {
        			boolQuery.must(QueryBuilders
        					.rangeQuery("unitsp_value.keyword")
        					.from(filter.getRangeGt())
        					.to(filter.getRangeLt())
        					.includeLower(filter.isIncludeLower())
                            .includeUpper(filter.isIncludeUpper()));
        			boolQuery.must(QueryBuilders.termQuery("specgr_id", filter.getFilterCd()));
        			boolQuery.must(QueryBuilders.termQuery("specitm_seq", filter.getLabelCd()));
        		}
        	}
        }
		boolQuery.filter(QueryBuilders.termsQuery("unit_nr", listOfUnit.getUnits()));
	}

    private AssetSearchResponse setData(SearchHit hit) {
    	AssetSearchResponse assetSearchResponse = new AssetSearchResponse();
    	assetSearchResponse.setUnitNumber((Integer) hit.getSource().get("unit_nr"));
    	assetSearchResponse.setLicenceNumber((String) hit.getSource().get("unit_licence_nr"));
    	assetSearchResponse.setOperationalStatus((String) hit.getSource().get("unit_operational_status_desc"));
    	assetSearchResponse.setInOutStatus((String) hit.getSource().get("unit_in_out_status_desc"));
    	assetSearchResponse.setPhysicalCompanyNumber((Integer) hit.getSource().get("unit_physical_company_nr"));
    	assetSearchResponse.setRespnsblCompanyNumber((Integer) hit.getSource().get("unit_respnsbl_company_nr"));
    	assetSearchResponse.setLastParkLocCd((String) hit.getSource().get("unit_last_park_loc_cd"));
    	assetSearchResponse.setLastIntchKey((Object) hit.getSource().get("unit_last_intch_key"));
    	assetSearchResponse.setCountry((String) hit.getSource().get("physical_country_name"));
    	assetSearchResponse.setCategoryGroupCode((String) hit.getSource().get("unit_catgrp_code"));
    	assetSearchResponse.setRagStatus((String) hit.getSource().get("unit_status"));
    	assetSearchResponse.setPhysicalBranchNumber((Integer) hit.getSource().get("unit_physical_branch_nr"));
    	assetSearchResponse.setRespnsblBranchNumber((Integer) hit.getSource().get("unit_respnsbl_branch_nr"));
    	assetSearchResponse.setSerialNumber((String) hit.getSource().get("unit_serial_nr"));
    	assetSearchResponse.setManufacturer((String) hit.getSource().get("unit_manufacturer"));
    	if("Y".equalsIgnoreCase((String) hit.getSource().get("unit_available_for_sale_ind"))) {
    		assetSearchResponse.setAvailableForSale("Yes");
    	}
    	else {
    		assetSearchResponse.setAvailableForSale("No");
    	}    	
    	assetSearchResponse.setCustomerAssetNumber((String) hit.getSource().get("unit_customer_refrnc"));
    	assetSearchResponse.setLicenceCountryName((String) hit.getSource().get("unit_licence_country_name"));
    	assetSearchResponse.setCustomerName((String) hit.getSource().get("customer_name"));
    	assetSearchResponse.setIsThirdPartyAsset((String) hit.getSource().get("is_thirdparty"));
    	assetSearchResponse.setEquipmentOwnerStatus((String) hit.getSource().get("equipment_owner_status"));
    	assetSearchResponse.setCustomerNr((Integer) hit.getSource().get("customer_nr"));
    	assetSearchResponse.setEquipmentType((String) hit.getSource().get("equipment_type"));
    	assetSearchResponse.setUnitCd((String) hit.getSource().get("unit_cd"));
    	assetSearchResponse.setCustomerNumberCombi((Double) hit.getSource().get("customer_number_combi"));
    	assetSearchResponse.setMotDate((String) hit.getSource().get("mot_date"));
    	assetSearchResponse.setReservation((String) hit.getSource().get("reservation"));
    	assetSearchResponse.setPoNumber((String) hit.getSource().get("po_number"));
    	assetSearchResponse.setUnitComment((String) hit.getSource().get("unit_comment"));
    	assetSearchResponse.setUnitStatusComment((String) hit.getSource().get("unit_status_comment"));
    	assetSearchResponse.setRegion((String) hit.getSource().get("region"));
    	assetSearchResponse.setRemarketingFlag((String) hit.getSource().get("remarketing_flag"));
    	assetSearchResponse.setImageCount((Integer) hit.getSource().get("image_count"));
    	assetSearchResponse.setBcheckServiceDate((String) hit.getSource().get("bcheck_service_date"));
    	assetSearchResponse.setReeferServiceDate((String) hit.getSource().get("reefer_service_date"));
    	assetSearchResponse.setCustomerNameNew((String) hit.getSource().get("customer_name_new"));
    	assetSearchResponse.setLastDieselHrs((Integer) hit.getSource().get("unit_last_diesel_hours"));
    	assetSearchResponse.setLastElectricHrs((Integer) hit.getSource().get("unit_last_electric_hours"));
    	assetSearchResponse.setLastKm((Integer) hit.getSource().get("unit_last_km"));
    	assetSearchResponse.setUnitStatusDesc((String) hit.getSource().get("unit_status_desc"));
    	assetSearchResponse.setModelYear((Integer) hit.getSource().get("unit_model_year"));
    	assetSearchResponse.setOldCatGroupCd((String) hit.getSource().get("old_cat_cd"));
    	assetSearchResponse.setPfr((String) hit.getSource().get("unit_lien_cd"));
    	assetSearchResponse.setSoldDate((String) hit.getSource().get("unit_sold_date"));
    	assetSearchResponse.setUnitRefurbComment((String) hit.getSource().get("unit_refurb_comment"));
    	assetSearchResponse.setTsp1((String) hit.getSource().get("tsp1"));
    	assetSearchResponse.setUnitNewUsedLeasedInd((String) hit.getSource().get("unit_new_used_leased_ind"));
    	
		return assetSearchResponse;
	}
    
    private AssetSearchResponse setDataAssetSpecs(SearchHit hit) {
    	Filter filter = new Filter();
    	filter.setValue(Integer.toString((Integer) hit.getSource().get("unit_nr")));
    	filter.setCondition(AssetConstants.FILTER_AND_CONDITION);
		filter.setElasticDbField("unit_nr");
		filter.setOperation(AssetConstants.FILTER_EQUALS_OPERATION);
		
		AssetSearchResponse assetSearchResponse = new AssetSearchResponse();
    	assetSearchResponse.setUnitNumber((Integer) hit.getSource().get("unit_nr"));
    	assetSearchResponse.setLicenceNumber((String) hit.getSource().get("unitsp_value"));
    	assetSearchResponse.setPhysicalBranchNumber((Integer) hit.getSource().get("specgr_id"));
    	assetSearchResponse.setRespnsblBranchNumber((Integer) hit.getSource().get("specitm_seq"));
		return assetSearchResponse;
	}
    
    public boolean checkIntValue(String val)
    {
    	boolean flag = true;
    	try{
    	Integer.parseInt(val);
    	}
    	catch(NumberFormatException e){
    		flag = false;
    	}
		return flag;
    }
}