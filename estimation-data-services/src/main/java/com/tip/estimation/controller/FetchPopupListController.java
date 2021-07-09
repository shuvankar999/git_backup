package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.FetchEstnPopupDetailsRequest;
import com.tip.estimation.service.FetchPopupListService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchPopupListController {

	@Autowired
	FetchPopupListService fetchPopupListService;

	@RequestMapping(value = "estnPopupList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPopupList(@RequestBody FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest) {

		return fetchPopupListService.getPopupList(fetchEstnPopupDetailsRequest);

	}

}
