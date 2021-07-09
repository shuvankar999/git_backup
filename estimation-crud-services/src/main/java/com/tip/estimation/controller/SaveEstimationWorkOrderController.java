package com.tip.estimation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimation.model.SaveEstnWorkOrderRequest;
import com.tip.estimation.service.SaveEstimationWorkOrderService;

@RestController
@RequestMapping("/service/estimation-crud-service/1.0/")
public class SaveEstimationWorkOrderController {
	

	@Autowired
	SaveEstimationWorkOrderService saveEstimationWorkOrderService;


	@RequestMapping(value = "saveEstnWorkOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveEstnWorkOrder(@RequestBody SaveEstnWorkOrderRequest saveEstnWorkOrderRequest) {
		return saveEstimationWorkOrderService.saveEstnWorkOrder(saveEstnWorkOrderRequest);
	}
}


