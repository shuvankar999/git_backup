package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.model.FeeCardDetailsResponse;
import com.tip.supplier.service.FeeCardDetailsService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class FeeCardDetailsController {

	@Autowired
	FeeCardDetailsService feeCardDetailsService;

	@RequestMapping(value = "/fetchFeeCardDetails", method = RequestMethod.POST)
	@ResponseBody
	public FeeCardDetailsResponse fetchFeeCardDetails(@RequestBody FeeCardDetailsRequest feeCardDetailsRequest) {

		return feeCardDetailsService.fetchFeeCardDetails(feeCardDetailsRequest);
	}
}
