package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchEquipmentRequest;
import com.tip.estimation.service.FetchCustEquipmentDetailsService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchCustEquipmentDetailsController {

	@Autowired
	FetchCustEquipmentDetailsService fetchCustEquipmentDetailsService;

	@RequestMapping(value = "estCustomerEquipList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getEquipmentDetails(@RequestBody FetchEquipmentRequest fetchEquipmentRequest) {

		return fetchCustEquipmentDetailsService.getEquipmentDetails(fetchEquipmentRequest);
	}

}
