package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.SaveEstnTyreRdngRequest;
import com.tip.estimation.service.SaveEstnTyreReadingsService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class SaveEstnTyreReadingsController {
	
	@Autowired
	SaveEstnTyreReadingsService saveEstnTyreReadingsService;
	
	@RequestMapping(value = "saveEstnTyreReadings", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTyreReadings(@RequestBody SaveEstnTyreRdngRequest saveEstnTyreRdngRequest) {
		return saveEstnTyreReadingsService.saveTyreReadings(saveEstnTyreRdngRequest);

}
}
