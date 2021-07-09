package com.tip.estimationfilter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimationfilter.model.FetchFilterDetails;
import com.tip.estimationfilter.model.FetchFilterDetailsRequest;
import com.tip.estimationfilter.service.FetchFilterService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchFilterController {
	 
	@Autowired
	FetchFilterService fetchFilterService;
	

	@RequestMapping(value = "estFetchFilter", method = RequestMethod.POST)
	@ResponseBody
	public FetchFilterDetails getFilterDetails(@RequestBody FetchFilterDetailsRequest fetchFilterDetailsRequest) {
		return fetchFilterService.getFilterDetils(fetchFilterDetailsRequest);
	}

	

}
