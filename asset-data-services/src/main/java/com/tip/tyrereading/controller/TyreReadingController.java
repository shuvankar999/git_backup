package com.tip.tyrereading.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.model.TyreReadingResponse;
import com.tip.tyrereading.service.TyreReadingService;

@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class TyreReadingController {

	@Autowired
	TyreReadingService tyreReadingService;

	@RequestMapping(value = "fetchTyreReading", method = RequestMethod.POST)

	@ResponseBody
	public TyreReadingResponse getTyreReading(@Valid @RequestBody TyreReadingRequest tyreReadingRequest) {
		return tyreReadingService.getTyreReadingDetails(tyreReadingRequest);
	}
}