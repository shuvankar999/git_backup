package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.PartListRequest;
import com.tip.estimation.model.PartListResponse;
import com.tip.estimation.service.PartListService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class PartListController {

	@Autowired
	PartListService partListService;

	@RequestMapping(value = "estnPartList", method = RequestMethod.POST)
	@ResponseBody
	public PartListResponse getPartList(@RequestBody PartListRequest partListRequest) {

		return partListService.getPartList(partListRequest);

	}

}
