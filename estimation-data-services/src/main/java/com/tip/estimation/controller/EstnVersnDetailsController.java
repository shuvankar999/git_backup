package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.VersionDetailsRequest;
import com.tip.estimation.service.EstnVersnDetailsService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class EstnVersnDetailsController {
		
		@Autowired
		EstnVersnDetailsService estnVersnDetailsService;
		
		@RequestMapping(value = "estnVersionDetails", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getVersnDetails(@RequestBody VersionDetailsRequest versionDetailsRequest) {

			return estnVersnDetailsService.getVersnDetails(versionDetailsRequest);

		}
	
	}
