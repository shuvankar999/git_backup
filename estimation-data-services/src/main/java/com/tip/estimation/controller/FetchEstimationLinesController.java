package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EstimationLines;
import com.tip.estimation.service.FetchEstimationLinesService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchEstimationLinesController {
	
	@Autowired
	FetchEstimationLinesService fetchEstimationLinesService;
	
	@RequestMapping(value = "fetchEstnLines", method = RequestMethod.POST)
	@ResponseBody
	public EstimationLines  fetchEstLines(@RequestBody EstimationLines estnLines){
		return fetchEstimationLinesService.fetchEstLines(estnLines);
		
	}

}
