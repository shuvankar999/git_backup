package com.tip.estimationfilter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimationfilter.model.EnterpriseSearchDataEst;
import com.tip.estimationfilter.model.FetchFilterDetails;
import com.tip.estimationfilter.service.SaveProcessFilterService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class SaveProcessFilterController {

	@Autowired
	SaveProcessFilterService saveProcessFilterService;

	@RequestMapping(value = "estProcessFilter", method = RequestMethod.POST)
	@ResponseBody
	public EnterpriseSearchDataEst processFilterDetails(
			@RequestBody FetchFilterDetails fetchFilterDetails) {
		return saveProcessFilterService.getFilterDetils(fetchFilterDetails);
	}
}
