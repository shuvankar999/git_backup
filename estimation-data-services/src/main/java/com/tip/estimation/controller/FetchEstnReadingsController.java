package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchReadingsRequest;
import com.tip.estimation.service.FetchEstnReadingsService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchEstnReadingsController {
	
	@Autowired
	FetchEstnReadingsService fetchEstnReadingsService;
	
	@RequestMapping(value = "fetchReadings", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getReadings(@RequestBody FetchReadingsRequest fetchReadingsRequest) {

		return fetchEstnReadingsService.getReadings(fetchReadingsRequest);
	}

}
