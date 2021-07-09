package com.tip.fetchcapacityhrs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchcapacityhrs.model.CapacityHrsRequest;
import com.tip.fetchcapacityhrs.service.FetchCapacityHrsService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class FetchCapacityHrsController {

	@Autowired
	FetchCapacityHrsService fetchCapacityHrsService;

	@RequestMapping(value = "fetchCapacityHrs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCapacityHrs(@RequestBody CapacityHrsRequest request) {		
		return fetchCapacityHrsService.getCapacityHrs(request);
	}
}