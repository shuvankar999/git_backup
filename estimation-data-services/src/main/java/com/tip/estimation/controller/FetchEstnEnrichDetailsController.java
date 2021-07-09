package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchEnrichDetailsRequest;
import com.tip.estimation.model.FetchEnrichDetailsResponse;
import com.tip.estimation.service.FetchEstnEnrichDetailsService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class FetchEstnEnrichDetailsController {
		
		@Autowired
		FetchEstnEnrichDetailsService fetchEstnEnrichDetailsService;
		
		@RequestMapping(value = "fetchEstnEnrichDetails", method = RequestMethod.POST)
		@ResponseBody
		public FetchEnrichDetailsResponse  fetchEstEnrichDetails(@RequestBody FetchEnrichDetailsRequest fetchEnrichDetailsRequest){
			return fetchEstnEnrichDetailsService.fetchEstEnrichDetails(fetchEnrichDetailsRequest);
			
		}
	
	}
