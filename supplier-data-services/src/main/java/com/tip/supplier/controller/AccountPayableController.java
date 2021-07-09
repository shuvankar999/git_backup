package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.model.AccountPayableResponse;
import com.tip.supplier.service.AccountPayableService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class AccountPayableController {
	

	@Autowired
	AccountPayableService accountPayableService;
	
	@RequestMapping(value = "/fetchAccountPayable", method = RequestMethod.POST)
	@ResponseBody
	public AccountPayableResponse fetchAccountPayable(@RequestBody AccountPayableRequest accountPayableRequest) {

		return accountPayableService.fetchAccountPayable(accountPayableRequest);
	}
}
