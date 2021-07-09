package com.tip.equipmentdetails.controller;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.service.SaveAndProcessFilterService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class SaveAndProcessFilterController {
	
	public static final Logger logger = LoggerFactory.getLogger(SaveAndProcessFilterController.class);
	
	@Autowired
	SaveAndProcessFilterService saveAndProcessFilterService;
	
	@RequestMapping(value = "processFilterDetails", method = RequestMethod.POST)
	@ResponseBody
	public EnterpriseSearchData processFilterDetails(@RequestBody FetchFilterDetails fetchFilterDetails) {
		EnterpriseSearchData enterpriseSearchData = new EnterpriseSearchData();
		try {
			enterpriseSearchData = saveAndProcessFilterService.processFilterDetails(fetchFilterDetails,true);
		} catch (UnknownHostException e) {
			enterpriseSearchData.setErrorMsg("Exception Occured in processing filters");
			logger.error(enterpriseSearchData.getErrorMsg(), e);
		}
		return enterpriseSearchData;
	}
}