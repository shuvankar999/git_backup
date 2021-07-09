package com.tip.fetchdynamicmasterdata.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.fetchdynamicmasterdata.model.MasterDataDynamicResponse;
import com.tip.fetchdynamicmasterdata.service.FetchDynamicMasterDataService;


@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class FetchDynamicMasterDataController {

    @Autowired
    FetchDynamicMasterDataService fetchDynamicMasterDataService;

    @RequestMapping(value = "fetchDynamicMasterData", method = RequestMethod.POST)
    public MasterDataDynamicResponse getCategory(@Valid @RequestBody FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest) {
    	return fetchDynamicMasterDataService.getAllProcedures(fetchDynamicMasterDataRequest);
	}
}