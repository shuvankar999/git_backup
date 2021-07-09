package com.tip.estimation.service;

import com.tip.estimation.model.EstimationRequest;

@FunctionalInterface
public interface CreateUpdEstimationService {
	
	public Object createEstimation(EstimationRequest estimationRequest);

}
