package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.ConversionRequest;
import com.tip.estimation.service.ConvrsnToWpService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class ConvrsnToWpController {
		
		@Autowired
		ConvrsnToWpService convrsnToWpService;
		
		@RequestMapping(value = "estnCnvrsnToWp", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getConvrsnDetails(@RequestBody ConversionRequest conversionRequest) {
			
			return convrsnToWpService.getConvrsnDetails(conversionRequest);
		}
	
	}
