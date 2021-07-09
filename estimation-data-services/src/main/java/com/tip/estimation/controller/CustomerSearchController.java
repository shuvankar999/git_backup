package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.CustomerSearchRequest;
import com.tip.estimation.service.CustomerSearchService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class CustomerSearchController {
	 
	@Autowired
	CustomerSearchService customerSearchService;
	

	@RequestMapping(value = "customerSearch", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCustomer(@RequestBody CustomerSearchRequest customerSearchRequest) {
		
		return customerSearchService.getCustomer(customerSearchRequest);
	}

	

}
