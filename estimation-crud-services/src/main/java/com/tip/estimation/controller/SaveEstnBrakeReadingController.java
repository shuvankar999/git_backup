package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.SaveEstnBrakeRdngRequest;
import com.tip.estimation.service.SaveEstnBrakeReadingsService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class SaveEstnBrakeReadingController {
	
	@Autowired
	SaveEstnBrakeReadingsService saveEstnBrakeReadingsService;
	
	@RequestMapping(value = "saveEstnBrakeReadings", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBrakeReadings(@RequestBody SaveEstnBrakeRdngRequest saveEstnBrakeRdngRequest) {
		return saveEstnBrakeReadingsService.saveBrakeReadings(saveEstnBrakeRdngRequest);

}

}
