package com.tip.estimation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.CustomerBundleObj;
import com.tip.estimation.repository.AddCustomerBundleRepository;
import com.tip.estimation.service.AddCustomerBundleService;


@Service
public class AddCustomerBundleServiceImpl implements AddCustomerBundleService {
	
	static final Logger logger = LoggerFactory.getLogger(AddCustomerBundleServiceImpl.class);
	
	@Autowired
	AddCustomerBundleRepository addCustomerBundleRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CustomerBundleObj> addCustBundle(List<CustomerBundleObj> custBundleRequest) {
		List<CustomerBundleObj> listOfCustBundle = new ArrayList();
		int [] updateCount = addCustomerBundleRepository.addCustBundle(custBundleRequest);
		if (null != updateCount) {
			for (int i = 0; i < updateCount.length; i++) {
				CustomerBundleObj custBundle = new CustomerBundleObj();
				if (updateCount[i] == 0) {
					logger.error("Error_Code: FAILURE");
					custBundle = custBundleRequest.get(i);
					custBundle.setErrorCd("FAILED");
				} else {
					logger.error("Error_Code: SUCCESS");
					custBundle = custBundleRequest.get(i);
					custBundle.setErrorCd("SUCCESS");
				}
				listOfCustBundle.add(custBundle);
			}
		}
		return listOfCustBundle;
	}

}
