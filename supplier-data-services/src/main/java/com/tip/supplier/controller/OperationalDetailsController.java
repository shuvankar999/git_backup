package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.model.OperationalDetailsResponse;
import com.tip.supplier.service.OperationalDetailsService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class OperationalDetailsController {

	@Autowired
	OperationalDetailsService operationalDetailsService;

	@RequestMapping(value = "/fetchOperationalDetails", method = RequestMethod.POST)
	@ResponseBody
	public OperationalDetailsResponse fetchOperationalDetails(@RequestBody OperationalDetailsRequest operationalDetailsRequest) {

		return operationalDetailsService.fetchOperationalDetails(operationalDetailsRequest);
	}
}
