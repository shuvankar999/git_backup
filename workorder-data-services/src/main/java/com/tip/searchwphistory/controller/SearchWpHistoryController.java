package com.tip.searchwphistory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.searchwphistory.model.SearchRange;
import com.tip.searchwphistory.service.SearchWpHistoryService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class SearchWpHistoryController {

	@Autowired
	SearchWpHistoryService searchWpHistoryService;

	@RequestMapping(value = "searchWpHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getResourcePlanner(@RequestBody SearchRange searchRange) {
		return searchWpHistoryService.getWpHistory(searchRange);
	}
}