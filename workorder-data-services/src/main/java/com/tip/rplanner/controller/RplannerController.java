package com.tip.rplanner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.rplanner.model.ResourcePlannerRequest;
import com.tip.rplanner.model.ResourcePlannerResponse;
import com.tip.rplanner.service.RplannerService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class RplannerController {

	@Autowired
	RplannerService rPlannerService;

	@RequestMapping(value = "resourcePlan", method = RequestMethod.POST)
	@ResponseBody
	public ResourcePlannerResponse getResourcePlanner(@Valid @RequestBody ResourcePlannerRequest resourcePlannerRequest) {
		return rPlannerService.getResourcePlannerDetails(resourcePlannerRequest);
	}
}