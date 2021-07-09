package com.tip.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.customer.model.CustomerListRequest;
import com.tip.customer.repository.CustomerListRepository;
import com.tip.customer.service.CustomerDataService;
import com.tip.customer.service.CustomerListService;

@Service
@Transactional
public class CustomerListServiceImpl implements CustomerListService {
	
	
	 public static final Logger logger = LoggerFactory.getLogger(CustomerListServiceImpl.class);

	@Autowired
	CustomerListRepository customerListRepository;
	
	@Autowired
	CustomerDataService customerDataService;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> getCustomerList(CustomerListRequest customerListRequest) {
		Map<String, Object> returnMap = customerListRepository.getCustomerList(customerListRequest);
		Map<String, Object> responseMap =  new HashMap();
		for (Map.Entry<String, Object> entry : returnMap.entrySet())
		{
           if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
           	responseMap.put("CustomerList",(List<Map<String, Object>>) entry.getValue());
           }
		}
		return responseMap;
	}
	
}
