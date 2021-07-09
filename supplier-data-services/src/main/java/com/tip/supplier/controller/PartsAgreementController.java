package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.model.PartsAgreementResponse;
import com.tip.supplier.service.PartsAgreementService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class PartsAgreementController {

	@Autowired
	PartsAgreementService partsAgreementService;

	@RequestMapping(value = "/fetchPartsAgreement", method = RequestMethod.POST)
	@ResponseBody
	public PartsAgreementResponse fetchPartsAgreement(@RequestBody PartsAgreementRequest partsAgreementRequest) {

		return partsAgreementService.fetchPartsAgreement(partsAgreementRequest);
	}
}
