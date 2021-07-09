package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.service.SaveEnrichService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class EnrichDetailsSaveController {

	@Autowired
	SaveEnrichService saveEnrichService;
	
	@PostMapping("saveEnrichDetails")
	public EnrichResponse saveEnrichDetails(@RequestBody SaveEnrichDetails saveEnrichDetails) {
				
			return saveEnrichService.saveAll(saveEnrichDetails,false);
	
	}
	
	@PostMapping("saveSuppl")
	public EnrichResponse saveAllSuppl(@RequestBody SaveEnrichDetails saveEnrichDetails) {
		return saveEnrichService.saveAll(saveEnrichDetails,true);
	}

}
