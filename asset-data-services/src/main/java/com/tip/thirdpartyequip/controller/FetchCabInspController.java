package com.tip.thirdpartyequip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.thirdpartyequip.model.EquipmentCabRequest;
import com.tip.thirdpartyequip.model.EquipmentCabResponse;
import com.tip.thirdpartyequip.service.FetchCabInspService;


@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class FetchCabInspController {

	@Autowired
	FetchCabInspService fetchCabInspService;
	
	@RequestMapping(value = "fetchEquipCabDetails", method = RequestMethod.POST)
	@ResponseBody
	public EquipmentCabResponse getCabDetails(@RequestBody EquipmentCabRequest equipmentCabRequest) {
		return fetchCabInspService.getCabDetails(equipmentCabRequest);
	}
}
