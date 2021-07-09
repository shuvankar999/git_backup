package com.tip.thirdpartyequip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.thirdpartyequip.model.AccessoryRequest;
import com.tip.thirdpartyequip.service.FetchAccessoriesService;


@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class FetchAccessoriesController {

	@Autowired
	FetchAccessoriesService fetchAccessoriesService;
	
	@RequestMapping(value = "fetchAccessories", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fetchAccessories(@RequestBody AccessoryRequest accessoryRequest) {
		return fetchAccessoriesService.fetchAccessories(accessoryRequest);
	}
}
