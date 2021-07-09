package com.tip.estimationparts.controller;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimationparts.model.PartListResponse;
import com.tip.estimationparts.model.PartsRequest;
import com.tip.estimationparts.service.FetchEstnPartListService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class FetchEstnPartListController {
		
		@Autowired
		FetchEstnPartListService fetchEstnPartListService;
		
		@RequestMapping(value = "estnFilterFetchParts", method = RequestMethod.POST)
		@ResponseBody
		public PartListResponse getFilterPartsList(@RequestBody PartsRequest partsRequest) throws UnknownHostException{
			return fetchEstnPartListService.getFilterPartsList(partsRequest);
			
		}
	
	}
