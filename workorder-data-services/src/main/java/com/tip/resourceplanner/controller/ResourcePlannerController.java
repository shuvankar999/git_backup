package com.tip.resourceplanner.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.model.ResourcePlannerResponse;
import com.tip.resourceplanner.service.ResourcePlannerService;


@RestController
@RequestMapping("/service/workorder-data-service/1.0/")
public class ResourcePlannerController {

    @Autowired
    ResourcePlannerService resourcePlannerService;

    @RequestMapping(value = "resourcePlan", method = RequestMethod.POST)
    @ResponseBody
    public ResourcePlannerResponse getResourcePlanner(@Valid @RequestBody ResourcePlannerRequest resourcePlannerRequest) {
    	return resourcePlannerService.getResourcePlannerDetails(resourcePlannerRequest);
    }
}