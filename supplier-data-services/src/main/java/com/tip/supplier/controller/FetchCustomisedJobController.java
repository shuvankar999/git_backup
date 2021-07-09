package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.FetchJobRequest;
import com.tip.supplier.service.FetchCustomisedJobService;
import com.tip.supplier.model.FetchJobResponse;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class FetchCustomisedJobController {

	@Autowired
	FetchCustomisedJobService fetchCustomisedJobService;

	@RequestMapping(value = "/fetchCustomisedJob", method = RequestMethod.POST)
	@ResponseBody
	public FetchJobResponse fetchJob(@RequestBody FetchJobRequest fetchJobRequest) {

		return fetchCustomisedJobService.fetchCustomisedJob(fetchJobRequest);
	}
}
