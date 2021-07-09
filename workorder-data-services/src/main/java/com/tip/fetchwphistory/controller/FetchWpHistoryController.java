package com.tip.fetchwphistory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchwphistory.model.WorkPackRequest;
import com.tip.fetchwphistory.service.FetchWpHistoryService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")

public class FetchWpHistoryController {

	@Autowired
	FetchWpHistoryService fetchWpHistoryService;

	@RequestMapping(value = "wpHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWpHistory(@RequestBody WorkPackRequest workPackRequest) {
		return fetchWpHistoryService.getWpHistory(workPackRequest);
	}
}