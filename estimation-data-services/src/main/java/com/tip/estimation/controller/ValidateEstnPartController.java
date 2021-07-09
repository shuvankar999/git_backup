package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.ValidatePartRequest;
import com.tip.estimation.service.ValidateEstnPartService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class ValidateEstnPartController {
	
		@Autowired
		ValidateEstnPartService validateEstnPartService;
		
		@RequestMapping(value = "validateEstnPart", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> validatePart(@RequestBody ValidatePartRequest validatePartRequest) {

			return validateEstnPartService.validatePart(validatePartRequest);

		}

		
	
	}
