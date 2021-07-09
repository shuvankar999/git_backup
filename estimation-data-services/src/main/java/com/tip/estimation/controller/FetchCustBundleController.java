package com.tip.estimation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchCustBundleRequest;
import com.tip.estimation.model.FetchCustBundleResponse;
import com.tip.estimation.service.FetchCustBundleService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchCustBundleController {
	
	@Autowired
	FetchCustBundleService fetchCustBundleService;
	
	@RequestMapping(value = "estCustomerBundle", method = RequestMethod.POST)
	@ResponseBody
	public List<FetchCustBundleResponse> getCustBundle(@RequestBody FetchCustBundleRequest fetchCustBundleRequest){
		
		return fetchCustBundleService.getCustBundle(fetchCustBundleRequest);
		
	}

}
