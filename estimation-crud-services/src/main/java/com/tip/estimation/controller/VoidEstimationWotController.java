package com.tip.estimation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.VoidEstnWotRequest;
import com.tip.estimation.model.VoidEstnWotResponse;
import com.tip.estimation.service.VoidEstimationWotService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class VoidEstimationWotController {
	
	@Autowired
	VoidEstimationWotService voidEstimationWotService;
	
	@RequestMapping(value = "voidEstnWot", method = RequestMethod.POST)
	@ResponseBody
	public  VoidEstnWotResponse voidEstnWot(@RequestBody VoidEstnWotRequest voidEstnWotRequest) {
		return voidEstimationWotService.voidEstnWot(voidEstnWotRequest);

	}

}
