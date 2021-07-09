package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.LabourRatesRequest;
import com.tip.supplier.model.LabourRatesResponse;
import com.tip.supplier.service.LabourRatesService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class LabourRatesController {

	@Autowired
	LabourRatesService labourRatesService;

	@RequestMapping(value = "/fetchLabourRates", method = RequestMethod.POST)
	@ResponseBody
	public LabourRatesResponse fetchLabourRates(@RequestBody LabourRatesRequest labourRatesRequest) {

		return labourRatesService.fetchLabourRates(labourRatesRequest);
	}
}
