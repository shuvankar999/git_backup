package com.tip.fetchfridgedetails.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;
import com.tip.fetchfridgedetails.service.TailliftDetailsService;

@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class TailliftDetailsController {

	
	@Autowired
	TailliftDetailsService tailliftDetailsService;
	
	@RequestMapping(value = "fetchTaillift", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTailliftDetails(@Valid @RequestBody FridgeDetailsRequest fridgeDetailsRequest) {
		return tailliftDetailsService.getTailliftDetails(fridgeDetailsRequest);
	}
}