package com.tip.suppliermasterdata.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.MasterDataDynamicResponse;
import com.tip.suppliermasterdata.service.FetchDynamicMasterDataService;


@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class FetchDynamicMasterDataController {

    @Autowired
    FetchDynamicMasterDataService fetchDynamicMasterDataService;

    @RequestMapping(value = "fetchDynamicMasterDataSupplier", method = RequestMethod.POST)
    public MasterDataDynamicResponse getCategory(@Valid @RequestBody FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest) {
    	return fetchDynamicMasterDataService.getAllProcedures(fetchDynamicMasterDataRequest);
	}
}