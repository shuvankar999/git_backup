package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EquipSearchRequest;
import com.tip.estimation.service.EquipSearchService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class EquipSearchController {

	@Autowired
	EquipSearchService equipSearchService;
	
	@RequestMapping(value = "equipSearchEst", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEquipment(@RequestBody EquipSearchRequest equipSearchRequest) {

		return equipSearchService.getEquipment(equipSearchRequest);
	}

}
