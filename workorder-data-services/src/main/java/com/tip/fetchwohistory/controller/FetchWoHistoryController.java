package com.tip.fetchwohistory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchwohistory.model.WorkOrderRequest;
import com.tip.fetchwohistory.service.FetchWoHistoryService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")

public class FetchWoHistoryController {

	@Autowired
	FetchWoHistoryService fetchWoHistoryService;

	@RequestMapping(value = "woHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getWoHistory(@RequestBody WorkOrderRequest workOrderRequest) {
		return fetchWoHistoryService.getWoHistory(workOrderRequest);
	}
}