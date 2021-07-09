package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.EstRequest;
import com.tip.estimation.service.FetchIntchService;

@RestController
@RequestMapping("/service/estimation-data-service/1.0/")
public class FetchIntchController {
	
	@Autowired
	FetchIntchService fetchIntchService;
	

	@RequestMapping(value = "fetchIntchList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getIntchDetails(@RequestBody EstRequest estRequest) {
		
		return fetchIntchService.getIntchDetails(estRequest);
	}

	

}
