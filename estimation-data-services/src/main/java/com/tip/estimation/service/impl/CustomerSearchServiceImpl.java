package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.CustomerSearchRequest;
import com.tip.estimation.repository.CustomerSearchRepository;
import com.tip.estimation.service.CustomerSearchService;

@Service
@Transactional
public class CustomerSearchServiceImpl implements CustomerSearchService {
	
	@Autowired
	CustomerSearchRepository customerSearchRepository;

	@Override
	public Map<String, Object> getCustomer(CustomerSearchRequest customerSearchRequest) {

		Map<String, Object> resultMap = customerSearchRepository.getCustomer(customerSearchRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("CustomerList", lst);
			}
		}
		
		return returnMap;
	}
	

}
