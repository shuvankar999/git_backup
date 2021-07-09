package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchEstnConsumablesRequest;
import com.tip.estimation.model.FetchEstnConsumablesResponse;
import com.tip.estimation.service.FetchEstnConsumablesDetailsService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchEstnConsumablesDetailsController {

	@Autowired
	FetchEstnConsumablesDetailsService fetchEstnConsumablesDetailsService;

	@RequestMapping(value = "estnConsmbleDetails", method = RequestMethod.POST)
	@ResponseBody
	public FetchEstnConsumablesResponse getConsumablesDetails(
			@RequestBody FetchEstnConsumablesRequest fetchEstnConsumablesRequest) {

		return fetchEstnConsumablesDetailsService.getConsumablesDetails(fetchEstnConsumablesRequest);

	}

}
