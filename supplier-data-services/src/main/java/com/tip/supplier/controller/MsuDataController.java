package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.MsuDataRequest;
import com.tip.supplier.model.MsuDataResponse;
import com.tip.supplier.service.MsuDataService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class MsuDataController {

	@Autowired
	MsuDataService msuDataService;

	@RequestMapping(value = "/fetchMsuData", method = RequestMethod.POST)
	@ResponseBody
	public MsuDataResponse fetchMsuData(@RequestBody MsuDataRequest msuDataRequest) {

		return msuDataService.fetchMsuData(msuDataRequest);
	}
}
