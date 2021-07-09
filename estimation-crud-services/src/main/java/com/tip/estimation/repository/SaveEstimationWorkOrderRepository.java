package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.SaveEstnWorkOrderRequest;

@FunctionalInterface
public interface SaveEstimationWorkOrderRepository {

	Map<String, Object> saveEstnWorkOrder(SaveEstnWorkOrderRequest saveEstnWorkOrderRequest);
	
}
