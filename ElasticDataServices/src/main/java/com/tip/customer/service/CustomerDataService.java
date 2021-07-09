package com.tip.customer.service;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.search.SearchHit;

import com.tip.customer.model.ArrayOfCustomerData;
import com.tip.customer.model.CustomerSearchResponse;

public interface CustomerDataService {
   
	ArrayOfCustomerData getCustomerData(Map<String, String> fields) throws UnknownHostException;
	Long getCustomerDataCount(HashMap<String, String> fields) throws UnknownHostException;
	ArrayOfCustomerData customerDataTextSearch(String text) throws UnknownHostException;
	public Long customerDataTextSearchCount(String text) throws UnknownHostException;
	
	public ArrayOfCustomerData customerDataDefaultSearch(String ssoId) throws UnknownHostException;
	public CustomerSearchResponse setData(SearchHit hit);

	public List getDistinctCustomerList(String elsticDbColumn, String moduleName)
			throws UnknownHostException, InterruptedException, ExecutionException;
}