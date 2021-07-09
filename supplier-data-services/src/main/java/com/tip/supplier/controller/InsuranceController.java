package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.InsuranceRequest;
import com.tip.supplier.model.InsuranceResponse;
import com.tip.supplier.service.InsuranceService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;

	@RequestMapping(value = "/fetchInsuranceDetails", method = RequestMethod.POST)
	@ResponseBody
	public InsuranceResponse fetchInsuranceDetails(@RequestBody InsuranceRequest insuranceRequest) {

		return insuranceService.fetchInsuranceDetails(insuranceRequest);
	}
}
