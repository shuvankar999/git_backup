package com.tip.supplier.service.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
import com.tip.supplier.model.ArrayOfSupplierData;
import com.tip.supplier.model.MasterdataRequest;
import com.tip.supplier.model.SupplierSearchResponse;
import com.tip.supplier.service.SupplierDataService;
import com.tip.supplier.util.SupplierDataConstants;

@Service
public class SupplierDataServiceImpl implements SupplierDataService{
	
	@Autowired
	private ElasticUtil elasticUtil;
	
	@Value("${elasticsearch.index_supplier}")
	private String esIndex;

	@Value("${elasticsearch.type_supplier}")
	private String esType;

	static final Logger logger = LoggerFactory.getLogger(SupplierDataServiceImpl.class);

	@Override
	public Long supplierDataTextSearchCount(String text) throws UnknownHostException {
		Long count = 0L;
		TransportClient client = elasticUtil.getElasticSearchClient();
		QueryBuilder qb;
		if (text.matches("[0-9]+")) {
			qb = QueryBuilders.queryStringQuery("*" + text + "*")
					.field("vendor_id_str")
					.field("vendor_name");
		} else if (text.matches("^[A-Za-z, ]++$")) {
			qb = QueryBuilders.queryStringQuery("\"*" + text + "*\"")
					.field("vendor_id_str")
					.field("vendor_name");
		} else if (text.matches("[a-zA-Z0-9]*")) {
			qb = QueryBuilders.queryStringQuery("*" + text + "*")
					.field("vendor_id_str")
					.field("vendor_name");
		} else {
			qb = QueryBuilders.queryStringQuery("\"*"+ text + "*\"")
					.field("vendor_id_str")
					.field("vendor_name");
		}
		SearchResponse response = client.prepareSearch(esIndex).setQuery(qb).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
				.actionGet();
		count = response.getHits().getTotalHits();
		return count;
	}
	
	@Override
	public ArrayOfSupplierData getSupplierMasterdata(MasterdataRequest masterdataRequest){
		
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		createQuery(masterdataRequest, boolQuery);
		ArrayOfSupplierData arrayOfSupplierData = new ArrayOfSupplierData();
		SearchResponse response = null;
		long nbHits = 0;
		try{
			TransportClient client = elasticUtil.getElasticSearchClient();
			response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType).setScroll(new TimeValue(60000)).get();
        	nbHits = response.getHits().getTotalHits();
        	System.out.println("***********Total Hits from supplier index*******"+nbHits);
			do {
					response.getHits().forEach(hit -> {
						SupplierSearchResponse supplierSearchResponse = setData(hit);
						arrayOfSupplierData.getSupplierDataItem().add(supplierSearchResponse);
				}
        	);
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (response.getHits().getHits().length != 0);
			
        }catch(Exception e){
        	logger.error("Error occurred: "+e);
        }
		arrayOfSupplierData.setCount(nbHits);
		return arrayOfSupplierData;
		
	}
	
	private void createQuery(MasterdataRequest masterdataRequest, BoolQueryBuilder boolQuery) {
		String vendorStatus = masterdataRequest.getVendorStatus();
		String[] vendorStatusArr = vendorStatus.split(",");
    	boolQuery.must(QueryBuilders.termQuery("vendor_company_nr", masterdataRequest.getCompanyNr()));
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
		if(masterdataRequest.getListOfSearchText()!=null&&!masterdataRequest.getListOfSearchText().isEmpty()){
			for(String searchTextValue:masterdataRequest.getListOfSearchText()){
				likeOperation(searchTextValue, boolQuery);
			}
		}
		
	}
	
	private void likeOperation(String value, BoolQueryBuilder boolQuery){
		QueryBuilder qb;
		 if(value.matches("[0-9]+") || value.matches("[a-zA-Z0-9]*") || (!value.contains(" ") && value.matches("^[A-Za-z, ]++$"))){
			 qb = QueryBuilders.queryStringQuery("*"+ value + "*")
       			.field("vendor_id_str").field("vendor_id_str").field("vendor_city").splitOnWhitespace(false);
		 }else{
			 qb = QueryBuilders.queryStringQuery("\"*"+ value + "*\"")
             		.field("vendor_id_str").field("vendor_id_str").field("vendor_city").splitOnWhitespace(false);
		 }
		 boolQuery.must(qb);
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
		supplierSearchResponse.setVendorCompanyNr((Integer) hit.getSource().get("vendor_company_nr"));
		supplierSearchResponse.setVendorEmail((String) hit.getSource().get("vendor_email"));
		supplierSearchResponse.setIsGetRemote((String) hit.getSource().get("is_getremote"));
		supplierSearchResponse.setLabourRate((Double) hit.getSource().get("labour_rate"));
		supplierSearchResponse.setConsolidatedPo((String) hit.getSource().get("consolidated_po"));
		supplierSearchResponse.setInternalWorkshop((String) hit.getSource().get("internal_workshop"));
		supplierSearchResponse.setPaymentVendorId((Integer) hit.getSource().get("payment_vendor_id"));
		supplierSearchResponse.setVendorTelNumber((String) hit.getSource().get("vendor_tel_nr"));
		return supplierSearchResponse;
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
