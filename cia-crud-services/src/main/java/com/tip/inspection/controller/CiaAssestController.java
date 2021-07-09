package com.tip.inspection.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.inspection.model.AddCiaAssestRequest;
import com.tip.inspection.model.AddCiaAssestResponse;
import com.tip.inspection.service.CiaAssestService;

@RestController
@RequestMapping("/service/cia-crud-service/1.0/")
public class CiaAssestController {
	
	@Autowired
	CiaAssestService saveCiaAssestService;

	@RequestMapping(value = "/addCiaAsset", method = RequestMethod.POST)
	@ResponseBody
	public AddCiaAssestResponse saveUserProfile(@RequestBody @Valid AddCiaAssestRequest addCiaAssestRequest) {
		return saveCiaAssestService.saveCiaAssest(addCiaAssestRequest);
	}
}
