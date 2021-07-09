package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EnrichAddtionalRequest;
import com.tip.estimation.model.EnrichAddtionalResponse;
import com.tip.estimation.service.EnrichAddtnlService;



	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class EnrichAddtnlController {
		
		@Autowired
		EnrichAddtnlService enrichAddtnlService;
		
		@RequestMapping(value = "fetchEnrichAddtnlDetails", method = RequestMethod.POST)
		@ResponseBody
		public EnrichAddtionalResponse fetchAddtnlEnrichDetails(@RequestBody EnrichAddtionalRequest enrichAddtionalRequest){
			return enrichAddtnlService.fetchAddtnlEnrichDetails(enrichAddtionalRequest);
			
		}
	
	
	}
