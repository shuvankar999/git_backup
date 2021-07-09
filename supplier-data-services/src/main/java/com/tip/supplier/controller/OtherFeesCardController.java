package com.tip.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.model.OtherFeesCardResponse;
import com.tip.supplier.service.OtherFeesCardService;

@RestController
@RequestMapping("/service/supplier-data-service/2.0/")
public class OtherFeesCardController {

	@Autowired
	OtherFeesCardService otherFeesCardService;

	@RequestMapping(value = "/fetchOtherFeesCard", method = RequestMethod.POST)
	@ResponseBody
	public OtherFeesCardResponse fetchOtherFeesCard(@RequestBody OtherFeesCardRequest otherFeesCardRequest) {
		return otherFeesCardService.fetchOtherFeesCard(otherFeesCardRequest);
	}
}
