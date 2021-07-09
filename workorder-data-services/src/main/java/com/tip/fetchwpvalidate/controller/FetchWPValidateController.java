package com.tip.fetchwpvalidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.model.FetchWPValidateResponse;
import com.tip.fetchwpvalidate.service.FetchWPValidateService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class FetchWPValidateController {

	@Autowired
	FetchWPValidateService fetchWPValidateService;

	@RequestMapping(value = "fetchWPToValidate", method = RequestMethod.POST)
	@ResponseBody
	public FetchWPValidateResponse getAttentionDetails(@RequestBody FetchWPValidateRequest fetchWPValidateRequest) {		
		return fetchWPValidateService.getWPValidateDetails(fetchWPValidateRequest);
	}
}