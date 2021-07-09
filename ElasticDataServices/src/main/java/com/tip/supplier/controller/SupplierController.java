package com.tip.supplier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;
import com.tip.supplier.model.ArrayOfSupplierData;
import com.tip.supplier.model.MasterdataRequest;
import com.tip.supplier.service.SupplierDataService;
import com.tip.supplier.service.SupplierFetchFilterService;
import com.tip.supplier.service.SupplierProcessFilterService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class SupplierController {

    @Autowired
    SupplierFetchFilterService supplierFetchFilterService;
    
    @Autowired
    SupplierProcessFilterService supplierProcessFilterService;
    
    @Autowired
    SupplierDataService supplierDataService;
    
    public static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    
	@RequestMapping(value = "supplierFetchFilter", method = RequestMethod.POST)
	@ResponseBody
	public FetchFilterDetails getFilterDetails(@RequestBody FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		return supplierFetchFilterService.getFilterDetils(fetchFilterDetailsRequest);
	}
	
	@RequestMapping(value = "supplierProcessFilter", method = RequestMethod.POST)
	@ResponseBody
	public EnterpriseSearchData processFilterDetails(
			@RequestBody FetchFilterDetails fetchFilterDetails) {
		return supplierProcessFilterService.processFilterDetails(fetchFilterDetails);
	}
	
	@PostMapping("supplierMasterdataList")
	public ArrayOfSupplierData getMasterdataList(@RequestBody MasterdataRequest masterdataRequest){
		return supplierDataService.getSupplierMasterdata(masterdataRequest);
		
	}
}