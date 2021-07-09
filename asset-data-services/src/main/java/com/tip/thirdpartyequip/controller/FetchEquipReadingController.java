package com.tip.thirdpartyequip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.thirdpartyequip.model.EquipmentReadingRequest;
import com.tip.thirdpartyequip.service.FetchEquipReadingService;


@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class FetchEquipReadingController {

	
	@Autowired
	FetchEquipReadingService fetchEquipReadingService;
	
	@RequestMapping(value = "EquipReadingFetchThirdParty", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fetchEquipReading(@RequestBody EquipmentReadingRequest equipmentReadingRequest) {
		return fetchEquipReadingService.fetchEquipReading(equipmentReadingRequest);
	}
}
