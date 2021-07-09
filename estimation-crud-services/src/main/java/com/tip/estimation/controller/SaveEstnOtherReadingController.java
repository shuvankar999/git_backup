package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.SaveEstnOtherReadingRequest;
import com.tip.estimation.service.SaveEstnOtherReadingService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class SaveEstnOtherReadingController {

	@Autowired
	SaveEstnOtherReadingService saveEstnOtherReadingService;

	@RequestMapping(value = "saveEstnOtherReadings", method = RequestMethod.POST)
	@ResponseBody
	public Object saveEstnOtherReading(@RequestBody SaveEstnOtherReadingRequest saveEstnOtherReadingRequest) {
		return saveEstnOtherReadingService.saveEstnOtherReading(saveEstnOtherReadingRequest);

	}
}
