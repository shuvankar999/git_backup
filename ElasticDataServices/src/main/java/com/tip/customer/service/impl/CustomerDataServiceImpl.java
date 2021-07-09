package com.tip.customer.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tip.asset.model.AssetSearchResponse;
import com.tip.customer.model.ArrayOfCustomerData;
import com.tip.customer.model.CustomerSearchResponse;
import com.tip.customer.repository.FetchProfileDataCustRepository;
import com.tip.customer.service.CustomerDataService;
import com.tip.elastic.util.ElasticSearchConstant;
import com.tip.elastic.util.ElasticUtil;
import com.tip.equipmentdetails.model.DropOption;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

	@Autowired
	private ElasticUtil elasticUtil;

	@Autowired
	FetchProfileDataCustRepository fetchProfileDataCustRepository;

	@Value("${elasticsearch.index_customer}")
	private String esIndex;

	@Value("${elasticsearch.type_customer}")
	private String esType;
		
	@Value("${elasticsearch.index_asset}")
    private String esIndexAsset;
    
    
    @Value("${elasticsearch.index_tp_asset}")
    private String esIndexTpAsset;
    
    @Value("${elasticsearch.type_asset}")
    private String esTypeAsset;
   
    
    @Value("${elasticsearch.type_tp_asset}")
    private String esTypeTpAsset;
	

	@Override
	public ArrayOfCustomerData getCustomerData(Map<String, String> fields) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : fields.entrySet()) {
			boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
		}
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		ArrayOfCustomerData arrayOfCustomerData = new ArrayOfCustomerData();
		do {
			for (SearchHit hit : response.getHits().getHits()) {
				CustomerSearchResponse customerSearchResponse = setData(hit);
				arrayOfCustomerData.getCustomerDataItem().add(customerSearchResponse);
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
		} while (response.getHits().getHits().length != 0);
		return arrayOfCustomerData;
	}

	@Override
	public Long getCustomerDataCount(HashMap<String, String> fields) throws UnknownHostException {
		Long count = 0L;
		TransportClient client = elasticUtil.getElasticSearchClient();
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();

		for (Map.Entry<String, String> entry : fields.entrySet()) {
			boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
		}
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		count = response.getHits().getTotalHits();
		return count;
	}

	@Override
	public ArrayOfCustomerData customerDataTextSearch(String text) throws UnknownHostException {
		TransportClient client = elasticUtil.getElasticSearchClient();
		QueryBuilder qb;
		if (checkIntValue(text)) {
			qb = QueryBuilders.queryStringQuery("*" + text + "*").field("customer_skey").field("customer_nr")
					.field("customer_number_combi").field("customer_name_abbreviation").field("customer_name")
					.field("name").field("upper_name").field("sfdc_external_account_id")
					.field("customer_registration_nr");
		} else {
			qb = QueryBuilders.queryStringQuery("\"*" + text + "*\"").field("customer_name_abbreviation")
					.field("customer_name").field("name").field("upper_name").field("sfdc_external_account_id")
					.field("customer_registration_nr");
		}
		SearchResponse response = client.prepareSearch(esIndex).setQuery(qb).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		ArrayOfCustomerData arrayOfCustomerData = new ArrayOfCustomerData();
		do {
			for (SearchHit hit : response.getHits().getHits()) {
				CustomerSearchResponse customerData = setData(hit);
				arrayOfCustomerData.getCustomerDataItem().add(customerData);
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
		} while (response.getHits().getHits().length != 0);
		arrayOfCustomerData.setCount(response.getHits().getTotalHits());
		return arrayOfCustomerData;
	}

	public CustomerSearchResponse setData(SearchHit hit) {
		CustomerSearchResponse customerData = new CustomerSearchResponse();

		customerData.setCustomerNr((Integer) hit.getSource().get("customer_nr"));
		customerData.setCustomerName((String) hit.getSource().get("customer_name"));
		customerData.setAddr((String) hit.getSource().get("addr"));
		customerData.setCity((String) hit.getSource().get("city"));
		customerData.setAaCountrySkey((Integer) hit.getSource().get("aa_country_skey"));
		customerData.setZipCd((String) hit.getSource().get("zip_cd"));
		customerData.setAaNote((String) hit.getSource().get("aa_note"));
		customerData.setEmailAddress((String) hit.getSource().get("email_address"));
		customerData.setCountryDialingCd((String) hit.getSource().get("country_dialing_cd"));
		customerData.setPhoneNumber((String) hit.getSource().get("phone_number"));
		if (hit.getSource().get("vat_number") != null) {
			String vatNum = hit.getSource().get("vat_number").toString();
			customerData.setVatNumberCountryCode(vatNum.substring(0, 2));
			customerData.setVatNum(vatNum.substring(2));
			customerData.setVatNumber(vatNum);
		}
		customerData.setCustomerLanguageCd((String) hit.getSource().get("customer_language_cd"));
		customerData.setCustomerSkey((Integer) hit.getSource().get("customer_skey"));
		customerData.setCustomerCompanyNr((Integer) hit.getSource().get("customer_company_nr"));
		customerData.setCustomerBranchNr((Integer) hit.getSource().get("customer_branch_nr"));
		customerData.setCustomerOperationsPostalCd((String) hit.getSource().get("customer_operations_postal_cd"));

		customerData.setCustomerInvoicePostalCd((String) hit.getSource().get("customer_invoice_postal_cd"));
		customerData.setCustomerBillingCompanyNr((Integer) hit.getSource().get("customer_billing_company_nr"));
		customerData.setCustomerBillingBranchNr((Integer) hit.getSource().get("customer_billing_branch_nr"));
		customerData.setCustomerOriginalBranchNr((Integer) hit.getSource().get("customer_original_branch_nr"));
		customerData.setCustomerAddedDate((String) hit.getSource().get("customer_added_date"));
		customerData.setCustomerStreetAddr((String) hit.getSource().get("customer_street_addr"));
		customerData.setCustomerCity((String) hit.getSource().get("customer_city"));
		customerData.setCustomerCountryCd((String) hit.getSource().get("customer_country_cd"));
		customerData.setCustomerStatusCd((String) hit.getSource().get("customer_status_cd"));
		customerData.setCustomerNumberCombi((Double) hit.getSource().get("customer_number_combi"));
		customerData.setVatNumber((String) hit.getSource().get("vat_number"));
		customerData.setPhoneSkey((Integer) hit.getSource().get("phone_skey"));
		customerData.setEntityId((Integer) hit.getSource().get("entity_id"));
		customerData.setEntityType((String) hit.getSource().get("entity_type"));
		customerData.setPhoneTypeRef((Integer) hit.getSource().get("phone_type_ref"));

		customerData.setSeq((Integer) hit.getSource().get("seq"));
		customerData.setCountrySkey((Integer) hit.getSource().get("country_skey"));
		customerData.setAddressSkey((Integer) hit.getSource().get("address_skey"));
		customerData.setAaEntityType((String) hit.getSource().get("aa_entity_type"));
		customerData.setAaEntityId((Integer) hit.getSource().get("aa_entity_id"));
		customerData.setAddressTypeRef((Integer) hit.getSource().get("address_type_ref"));
		customerData.setAaSeq((Integer) hit.getSource().get("aa_seq"));
		customerData.setAaCountrySkey((Integer) hit.getSource().get("aa_country_skey"));
		customerData.setName((String) hit.getSource().get("name"));
		customerData.setStatusRef((Integer) hit.getSource().get("status_ref"));
		customerData.setCompanyTypeRef((Integer) hit.getSource().get("company_type_ref"));
		customerData.setDataPrivacyId((Integer) hit.getSource().get("data_privacy_id"));
		customerData.setSfdcInternalAccountId((String) hit.getSource().get("sfdc_internal_account_id"));
		customerData.setSfdcExternalAccountId((String) hit.getSource().get("sfdc_external_account_id"));
		customerData.setTaxExemptReasonCd((String) hit.getSource().get("tax_exempt_reason_cd"));
		customerData.setFirstName((String) hit.getSource().get("first_name"));
		customerData.setLastName((String) hit.getSource().get("last_name"));
		customerData.setCountryName((String) hit.getSource().get("physical_country_name"));
		customerData.setStatus((String) hit.getSource().get("status"));
		customerData.setReadGroup((Integer) hit.getSource().get("read_group"));
		customerData.setUpdateGroup((Integer) hit.getSource().get("update_group"));
		customerData.setDeleteGroup((Integer) hit.getSource().get("delete_group"));
		return customerData;
	}

	@Override
	public Long customerDataTextSearchCount(String text) throws UnknownHostException {
		Long count = 0L;
		TransportClient client = elasticUtil.getElasticSearchClient();
		QueryBuilder qb;
		if (checkIntValue(text)) {
			qb = QueryBuilders.queryStringQuery("*" + text + "*").field("customer_skey").field("customer_nr")
					.field("customer_number_combi").field("customer_name_abbreviation").field("customer_name")
					.field("name").field("upper_name").field("sfdc_external_account_id")
					.field("customer_registration_nr");
		} else {
			qb = QueryBuilders.queryStringQuery("\"*" + text + "*\"").field("customer_name_abbreviation")
					.field("customer_name").field("name").field("upper_name").field("sfdc_external_account_id")
					.field("customer_registration_nr");
		}
		SearchResponse response = client.prepareSearch(esIndex).setQuery(qb).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();
		response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
				.actionGet();
		count = response.getHits().getTotalHits();
		return count;
	}

	@Override
	public ArrayOfCustomerData customerDataDefaultSearch(String ssoId) throws UnknownHostException {

		ArrayOfCustomerData arrayOfCustomerData = new ArrayOfCustomerData();

		TransportClient client = elasticUtil.getElasticSearchClient();

		List<Integer> listOfGroupNr = fetchProfileDataCustRepository.getDefaultProfileData(ssoId);
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();

		boolQuery.should(QueryBuilders.termsQuery("read_group", listOfGroupNr));
		boolQuery.should(QueryBuilders.termsQuery("update_group", listOfGroupNr));
		boolQuery.should(QueryBuilders.termsQuery("delete_group", listOfGroupNr));
		
		
		
		System.out.println(client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType));
		SearchResponse response = client.prepareSearch(esIndex).setQuery(boolQuery).setTypes(esType)
				.setScroll(new TimeValue(60000)).get();

		List<String> units = new ArrayList();
		long nbHits = response.getHits().getTotalHits();
		System.out.println("***********Total Hits from customer*******" + nbHits);
		do {
			for (SearchHit hit : response.getHits().getHits()) {
				CustomerSearchResponse customerData = setData(hit);
				arrayOfCustomerData.getCustomerDataItem().add(customerData);
			}
			response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
		} while (response.getHits().getHits().length != 0);

		arrayOfCustomerData.setCount(nbHits);
		return arrayOfCustomerData;
	}
	
	
	@Override
	public List getDistinctCustomerList(String elsticDbColumn, String entity)
			throws UnknownHostException, InterruptedException, ExecutionException {
		TransportClient client = elasticUtil.getElasticSearchClient();
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		boolQuery.must(QueryBuilders.existsQuery(elsticDbColumn));
		SearchRequestBuilder aggregationQuery = null;
		if(entity.equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_EQUIP)) {
			SearchResponse responseCount = client.prepareSearch(esIndexAsset, esIndexTpAsset).setQuery(boolQuery)
					.setTypes(esTypeAsset, esTypeTpAsset).setScroll(new TimeValue(60000)).get();
			int nbHits = Math.toIntExact(responseCount.getHits().getTotalHits());
			System.out.println(nbHits);
			aggregationQuery = client.prepareSearch(esIndexAsset, esIndexTpAsset).setQuery(boolQuery)
					.setTypes(esTypeAsset, esTypeTpAsset).addAggregation(AggregationBuilders.terms("distinct_" + elsticDbColumn)
							.field(elsticDbColumn + ".keyword").size(nbHits));
		}else if(entity.equalsIgnoreCase(ElasticSearchConstant.ENTITY_TYPE_CUSTOMER)) {
			SearchResponse responseCount = client.prepareSearch(esIndex).setQuery(boolQuery)
					.setTypes(esType).setScroll(new TimeValue(60000)).get();
			int nbHits = Math.toIntExact(responseCount.getHits().getTotalHits());
			System.out.println(nbHits);
			aggregationQuery = client.prepareSearch(esIndex).setQuery(boolQuery)
					.setTypes(esType).addAggregation(AggregationBuilders.terms("distinct_" + elsticDbColumn)
							.field(elsticDbColumn + ".keyword").size(nbHits));
		}
		
		SearchResponse response = aggregationQuery.execute().get();

		Terms terms = response.getAggregations().get("distinct_" + elsticDbColumn);
		List<DropOption> listOfDropOption = new ArrayList();
		terms.getBuckets().forEach(entry -> {
			DropOption dropOption = new DropOption();
			dropOption.setName((String) entry.getKey());
			dropOption.setValue((String) entry.getKeyAsString());
			listOfDropOption.add(dropOption);
		});
		return listOfDropOption;
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