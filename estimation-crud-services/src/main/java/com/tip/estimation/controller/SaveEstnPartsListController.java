package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.SaveEstnPartDetailsRequest;
import com.tip.estimation.service.SaveEstnPartsListService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
	public class SaveEstnPartsListController {
	
	@Autowired
	SaveEstnPartsListService saveEstnPartsListService;
	
	@RequestMapping(value = "saveEstnPartsList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveEstnPartDetails(@RequestBody SaveEstnPartDetailsRequest saveEstnPartDetailsRequest) {
		return saveEstnPartsListService.saveEstnPartDetails(saveEstnPartDetailsRequest);

}
	
	}
