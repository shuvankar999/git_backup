package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EstRequest;

@FunctionalInterface
public interface FetchOpenEstimationRepository {

	Map<String, Object> getOpenEstimation(EstRequest estRequest);

}
