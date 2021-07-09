package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.EstRequest;

@FunctionalInterface
public interface FetchOpenEstimationService {
	
	Map<String, Object> getOpenEstimation(EstRequest estRequest);

}
