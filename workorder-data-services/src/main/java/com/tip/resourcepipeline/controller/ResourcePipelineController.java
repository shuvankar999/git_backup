package com.tip.resourcepipeline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;
import com.tip.resourcepipeline.model.ResourcePipelineResponse;
import com.tip.resourcepipeline.service.ResourcePipelineService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class ResourcePipelineController {

	@Autowired
	ResourcePipelineService resourcePipelineService;

	@RequestMapping(value = "resourcePipeline", method = RequestMethod.POST)
	@ResponseBody
	public ResourcePipelineResponse getResourcePipeline(@Valid @RequestBody ResourcePipelineRequest resourcePipelineRequest) {
		return resourcePipelineService.getResourcePipelineDetails(resourcePipelineRequest);
	}
}