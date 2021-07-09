package com.tip.fetchwpextrahrs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchwpextrahrs.model.ExtraHrsRequest;
import com.tip.fetchwpextrahrs.service.FetchWpExtraHrsService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class FetchWpExtraHrsController {
	
	@Autowired
	FetchWpExtraHrsService fetchWpExtraHrsService;

	@RequestMapping(value = "fetchWpExtraHrs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWpExtraHrs(@RequestBody ExtraHrsRequest extraHrsRequest) {
		return fetchWpExtraHrsService.getWpExtraHrs(extraHrsRequest);
	}
}