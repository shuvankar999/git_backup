package com.tip.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.asset.model.EquipmentDetailsRequest;
import com.tip.asset.model.EquipmentDetailsResponse;
import com.tip.asset.service.FetchEquipmentService;


@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class FetchEquipmentController {
	
	@Autowired
	FetchEquipmentService fetchEquipmentService;
	
	@RequestMapping(value = "fetchEquipDetails", method = RequestMethod.POST)
	@ResponseBody
	public EquipmentDetailsResponse fetchEquipment(@RequestBody EquipmentDetailsRequest equipmentDetailsRequest) {
		return fetchEquipmentService.fetchEquipmentDetails(equipmentDetailsRequest);
	}
}