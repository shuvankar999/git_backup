package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EstimationRequest;
import com.tip.estimation.service.CreateUpdEstimationService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class CreateUpdEstimationController {
	
	@Autowired
	CreateUpdEstimationService createUpdEstimationService;
	

	@RequestMapping(value = "createUpdEstimation", method = RequestMethod.POST)
	@ResponseBody
	public Object createEstimation(@RequestBody EstimationRequest estimationRequest) {
		
		return createUpdEstimationService.createEstimation(estimationRequest);
	}

	

}
