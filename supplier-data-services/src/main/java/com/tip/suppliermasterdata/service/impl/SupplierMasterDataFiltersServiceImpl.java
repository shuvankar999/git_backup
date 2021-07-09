package com.tip.suppliermasterdata.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.supplier.main.ElasticUtil;
import com.tip.supplier.main.SupplierDataConstants;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.MasterDataDynamicResponse;
import com.tip.suppliermasterdata.model.SupplierData;
import com.tip.suppliermasterdata.service.SupplierMasterDataFiltersService;

@Service
public class SupplierMasterDataFiltersServiceImpl implements SupplierMasterDataFiltersService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;
    
    @Value("${elasticsearch.index_supplier}")
    private String esIndex;
    
    @Value("${elasticsearch.type_supplier}")
    private String esType;
    
    static final Logger logger = LoggerFactory.getLogger(SupplierMasterDataFiltersServiceImpl.class);
	@Override
	public void getSupplierElasticData(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest,
			MasterDataDynamicResponse lMasterDataDynamicResponse, List<String> vendorCategoryDescList) throws UnknownHostException{
    	TransportClient client = elasticUtil.getElasticSearchClient();
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        createQuery(fetchDynamicMasterDataRequest, boolQuery, vendorCategoryDescList);
        System.out.println(boolQuery.toString());
        SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        try{
        	long nbHits = response.getHits().getTotalHits();
        	System.out.println("***********Total Hits from supplier index*******"+nbHits);
			do {
					response.getHits().forEach(hit -> {
					SupplierData supplierData = setData(hit);
					lMasterDataDynamicResponse.getSupplierDetailsList().add(supplierData);
				}
        	);
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
			
        }catch(Exception e){
        	logger.error("Error occurred: "+e);
        	e.printStackTrace();
        }
    }


	private void createQuery(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest, BoolQueryBuilder boolQuery, List<String> vendorCategoryDescList) {
		String vendorStatus = fetchDynamicMasterDataRequest.getVendorStatus();
		String[] vendorStatusArr = vendorStatus.split(",");
    	boolQuery.must(QueryBuilders.termQuery("vendor_company_nr", fetchDynamicMasterDataRequest.getCompanyNr()));
    	List<Integer> vendorStatusList = new ArrayList<>();
		for(String status:vendorStatusArr){
			if(status.equalsIgnoreCase(SupplierDataConstants.VENDOR_STATUS_DESC_ACTIVE)) {
				vendorStatusList.add(SupplierDataConstants.VENDOR_STATUS_ACTIVE_NUMERIC);
	        }else if(status.equalsIgnoreCase(SupplierDataConstants.VENDOR_STATUS_DESC_DRAFT)) {
	        	vendorStatusList.add(SupplierDataConstants.VENDOR_STATUS_DRAFT_NUMERIC);
	        }else if(status.equalsIgnoreCase(SupplierDataConstants.VENDOR_STATUS_DESC_VOID)) {
	        	vendorStatusList.add(SupplierDataConstants.VENDOR_STATUS_VOID_NUMERIC);
	        }else if(status.equalsIgnoreCase(SupplierDataConstants.VENDOR_STATUS_DESC_ALL)) {
	        	vendorStatusList.add(SupplierDataConstants.VENDOR_STATUS_ACTIVE_NUMERIC);
	        	vendorStatusList.add(SupplierDataConstants.VENDOR_STATUS_DRAFT_NUMERIC);
	        	vendorStatusList.add(SupplierDataConstants.VENDOR_STATUS_VOID_NUMERIC);
	        }
		}
		boolQuery.filter(QueryBuilders.termsQuery("vendor_status", vendorStatusList));
		boolQuery.filter(QueryBuilders.termsQuery("vendor_category.keyword", vendorCategoryDescList));	
	}
    

    private SupplierData setData(SearchHit hit) {
    	SupplierData supplierData = new SupplierData();
    	supplierData.setVendorId((Integer) hit.getSource().get("vendor_id"));
    	supplierData.setVendorName((String) hit.getSource().get("vendor_name"));
    	supplierData.setIsGetremote((String) hit.getSource().get("is_getremote"));
    	supplierData.setConsolidatedPo((String) hit.getSource().get("consolidated_po"));
    	
    	return supplierData;
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