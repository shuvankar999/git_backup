package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchAdminFeesRequest;
import com.tip.estimation.model.FetchAdminFeesResponse;
import com.tip.estimation.service.FetchAdminFeesService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchAdminFeesController {

	@Autowired
	FetchAdminFeesService fetchAdminFeesService;

	@RequestMapping(value = "fetchAdminFees", method = RequestMethod.POST)
	@ResponseBody
	public FetchAdminFeesResponse fetchAdminFees(@RequestBody FetchAdminFeesRequest fetchAdminFeesRequest) {

		return fetchAdminFeesService.fetchAdminFees(fetchAdminFeesRequest);

	}

}
