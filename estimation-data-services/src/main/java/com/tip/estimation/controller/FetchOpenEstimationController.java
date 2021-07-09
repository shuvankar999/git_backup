package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EstRequest;
import com.tip.estimation.service.FetchOpenEstimationService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchOpenEstimationController {

	@Autowired
	FetchOpenEstimationService fetchOpenEstimationService;
	
	@RequestMapping(value = "OpenEstimationDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOpenEstimation(@RequestBody EstRequest estRequest) {

		return fetchOpenEstimationService.getOpenEstimation(estRequest);
	}

}
