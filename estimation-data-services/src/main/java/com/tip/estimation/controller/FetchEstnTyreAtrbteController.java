package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EstnTyreAttributeRequest;
import com.tip.estimation.service.FetchEstnTyreAtrbteService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class FetchEstnTyreAtrbteController {
		
		@Autowired
		FetchEstnTyreAtrbteService fetchEstnTyreAtrbteService;
		
		@RequestMapping(value = "estnTyreAttributeList", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getTyreAttribute(@RequestBody EstnTyreAttributeRequest estnTyreAttributeRequest) {

			return fetchEstnTyreAtrbteService.getTyreAttribute(estnTyreAttributeRequest);

		}
		
	
	}
