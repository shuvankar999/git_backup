package com.tip.common.controller;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.common.model.FilterRequest;
import com.tip.common.model.FilterResponse;
import com.tip.common.service.DnaSearchFilterService;


	@RestController
	@RequestMapping("/service/elastic-data-service/1.0/")
	public class DnaSearchFilterController {
		
		@Autowired
		DnaSearchFilterService dnaSearchFilterService;
		
		@RequestMapping(value = "searchDna", method = RequestMethod.POST)
		@ResponseBody
		public FilterResponse getFilterList(@RequestBody FilterRequest filterRequest) throws UnknownHostException{
			return dnaSearchFilterService.getFilterList(filterRequest);
			
		}
	
	}
