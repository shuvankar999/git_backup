package com.tip.estimation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.CustomerBundleObj;
import com.tip.estimation.service.AddCustomerBundleService;
	
	@RestController
	@RequestMapping("/service/estimation-crud-service/1.0/")
	public class AddCustomerBundleController {
		
		@Autowired
		AddCustomerBundleService addCustomerBundleService;
		
		@RequestMapping(value = "addCustomerBundle", method = RequestMethod.POST)
		@ResponseBody
		public List<CustomerBundleObj> addCustBundle(@Valid @RequestBody List<CustomerBundleObj> custBundleRequest ){
			return addCustomerBundleService.addCustBundle(custBundleRequest);
			
		}

}
