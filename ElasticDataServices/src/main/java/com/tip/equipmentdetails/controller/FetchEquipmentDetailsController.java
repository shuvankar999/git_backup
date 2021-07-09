package com.tip.equipmentdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.equipmentdetails.model.FetchEquipDetailsRequest;
import com.tip.equipmentdetails.model.FetchEquipDetailsResponse;
import com.tip.equipmentdetails.service.FetchEquipmentDetailsService;
@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class FetchEquipmentDetailsController {
	
	@Autowired
	FetchEquipmentDetailsService fetchEquipmentDetailsService;
	
	@RequestMapping(value = "fetchEquipmentDetails", method = RequestMethod.POST)
	@ResponseBody
	public FetchEquipDetailsResponse getEquipmentDetils(@RequestBody FetchEquipDetailsRequest fetchEquipDetailsRequest) {
		boolean refreshFlag = false;
		return fetchEquipmentDetailsService.getEquipmentDetils(fetchEquipDetailsRequest,refreshFlag);
	}
	
	@RequestMapping(value = "fetchRefreshedEquipmentDetails", method = RequestMethod.POST)
	@ResponseBody
	public FetchEquipDetailsResponse getRefreshedEquipmentDetils(@RequestBody FetchEquipDetailsRequest fetchEquipDetailsRequest) {
		boolean refreshFlag = true;
		return fetchEquipmentDetailsService.getEquipmentDetils(fetchEquipDetailsRequest,refreshFlag);
	}
}