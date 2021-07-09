package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EstnInspectionRequest;
import com.tip.estimation.service.EstnInspectionService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class EstnInspectionController {
		
		@Autowired
		EstnInspectionService estnInspectionService;
		
		
		@RequestMapping(value = "estnInspection", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getInspectionDetails(@RequestBody EstnInspectionRequest estnInspectionRequest) {
			
			return estnInspectionService.getInspectionDetails(estnInspectionRequest);
		}
	
	}
