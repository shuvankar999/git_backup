package com.tip.thirdpartyequip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.thirdpartyequip.model.EquipTyreReadingRequest;
import com.tip.thirdpartyequip.service.FetchEquipTyreReadingService;


@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class FetchEquipTyreReadingController {

	
	@Autowired
	FetchEquipTyreReadingService fetchEquipTyreReadingService;
	
	@RequestMapping(value = "TyreReadingFetchThirdParty", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fetchTyreReading(@RequestBody EquipTyreReadingRequest equipTyreReadingRequest) {
		return fetchEquipTyreReadingService.fetchEquipTyreReading(equipTyreReadingRequest);
	}
}
