package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.SaveEstnWorkOrderRequest;

@FunctionalInterface
public interface SaveEstimationWorkOrderService {

	Map<String, Object> saveEstnWorkOrder(SaveEstnWorkOrderRequest saveEstnWorkOrderRequest);
	

}
