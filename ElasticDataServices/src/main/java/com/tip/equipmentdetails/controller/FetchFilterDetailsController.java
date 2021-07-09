package com.tip.equipmentdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;
import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.service.FetchFilterDetailsService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class FetchFilterDetailsController {
	
	@Autowired
	FetchFilterDetailsService fetchFilterDetailsService;
	
	@RequestMapping(value = "fetchFilterDetails", method = RequestMethod.POST)
	@ResponseBody
	public FetchFilterDetails getEquipmentDetils(@RequestBody FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		return fetchFilterDetailsService.getFilterDetils(fetchFilterDetailsRequest);
	}
}