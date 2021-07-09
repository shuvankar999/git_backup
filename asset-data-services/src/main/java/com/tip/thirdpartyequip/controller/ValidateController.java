package com.tip.thirdpartyequip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.thirdpartyequip.model.ValidLicenseRequest;
import com.tip.thirdpartyequip.model.ValidSerialNrRequest;
import com.tip.thirdpartyequip.service.ValidateService;


@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class ValidateController {

	
	@Autowired
	ValidateService validateService;
	
	@RequestMapping(value = "validateSerial", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validateSerialNr(@RequestBody ValidSerialNrRequest validSerialNrRequest) {
		return validateService.validateSerialNr(validSerialNrRequest);
	}
	
	@RequestMapping(value = "validateLicense", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validateLicenseNr(@RequestBody ValidLicenseRequest validLicenseRequest) {
		return validateService.validateLicenseNr(validLicenseRequest);
	}
}
