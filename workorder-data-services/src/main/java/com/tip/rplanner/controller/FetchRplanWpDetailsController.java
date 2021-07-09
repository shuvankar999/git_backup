package com.tip.rplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.rplanner.model.WpRequest;
import com.tip.rplanner.service.FetchRplanWpDetailsService;

@RestController
@RequestMapping("/service/workorder-data-service/2.0/")
public class FetchRplanWpDetailsController {

	@Autowired
	FetchRplanWpDetailsService fetchRplanWpDetailsService;

	@RequestMapping(value = "fetchPopupWp", method = RequestMethod.POST)
	@ResponseBody
	public Object getWpdetails(@RequestBody WpRequest wpRequest) {
		return fetchRplanWpDetailsService.getWpdetails(wpRequest);
	}
}