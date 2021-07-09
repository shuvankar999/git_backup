package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.AllCapabilitiesRequest;
import com.tip.supplier.model.AllCapabilitiesResponse;
import com.tip.supplier.service.AllCapabilitiesService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class AllCapabilitiesController {

	@Autowired
	AllCapabilitiesService allCapabilitiesService;

	@RequestMapping(value = "/fetchAllCapabilities", method = RequestMethod.POST)
	@ResponseBody
	public AllCapabilitiesResponse fetchAllCapabilities(@RequestBody AllCapabilitiesRequest allCapabilitiesRequest) {
		return allCapabilitiesService.fetchAllCapabilities(allCapabilitiesRequest);
	}
}
