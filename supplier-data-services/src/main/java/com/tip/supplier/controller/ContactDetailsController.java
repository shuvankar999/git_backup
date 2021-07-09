package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.model.ContactDetailsResponse;
import com.tip.supplier.service.ContactDetailsService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class ContactDetailsController {

	@Autowired
	ContactDetailsService contactDetailsService;

	@RequestMapping(value = "/fetchContactDetails", method = RequestMethod.POST)
	@ResponseBody
	public ContactDetailsResponse fetchContactDetails(@RequestBody ContactDetailsRequest contactDetailsRequest) {
		return contactDetailsService.fetchContactDetails(contactDetailsRequest);
	}
}
