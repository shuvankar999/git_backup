package com.tip.fetchaxle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.model.FetchAxleResponse;
import com.tip.fetchaxle.service.FetchAxleService;

@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class FetchAxleController {

	@Autowired
	FetchAxleService fetchAxleService;


	@RequestMapping(value = "fetchNoOfAxles", method = RequestMethod.POST)

	@ResponseBody
	public FetchAxleResponse getNoOfAxles(@Valid @RequestBody FetchAxleRequest fetchAxleRequest) {
		return fetchAxleService.getNoOfAxlesForAsset(fetchAxleRequest);
	}
}