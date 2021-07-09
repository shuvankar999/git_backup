package com.tip.brakereading.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.model.BrakeReadingResponse;
import com.tip.brakereading.service.BrakeReadingService;

@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class BrakeReadingController {

	@Autowired
	BrakeReadingService brakeReadingService;
	
	@RequestMapping(value = "fetchBrakeInspReading", method = RequestMethod.POST)
	
	@ResponseBody
	public BrakeReadingResponse getBrakeReading(@Valid @RequestBody BrakeReadingRequest brakeReadingRequest) {
		return brakeReadingService.getBrakeReadingDetails(brakeReadingRequest);
	}
}