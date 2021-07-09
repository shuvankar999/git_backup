package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.PipelineRequest;
import com.tip.estimation.service.AddPipelineService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class AddPipelineController {
	
	@Autowired
	AddPipelineService addPipelineService;
	

	@RequestMapping(value = "addToPipeline", method = RequestMethod.POST)
	@ResponseBody
	public Object addToPipeline(@RequestBody PipelineRequest pipelineRequest) {
		
		return addPipelineService.addToPipeline(pipelineRequest);
	}

	

}
