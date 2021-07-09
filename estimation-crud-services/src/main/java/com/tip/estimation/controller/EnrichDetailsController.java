/*package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.service.EnrichDetailsService;


	@RestController
	@RequestMapping("/service/estimation-crud-service/1.0/")
	public class EnrichDetailsController {
		
		@Autowired
		EnrichDetailsService  enrichDetailsService;
		
		@RequestMapping(value = "saveEnrichDetails", method = RequestMethod.POST)
		@ResponseBody
		public EnrichResponse saveEnrichDetails(@RequestBody SaveEnrichDetails saveEnrichDetails) {
					
				return enrichDetailsService.saveEnrichDetails(saveEnrichDetails);
		
		}
	}
*/