package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EstimationRequest;

@FunctionalInterface
public interface CreateUpdEstimationRepository {

	Map<String, Object> createEstimation(EstimationRequest estimationRequest);

}
