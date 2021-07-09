package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EstimationLines;

@FunctionalInterface
public interface FetchEstimationLinesRepository {
	
	public Map<String, Object> fetchEstLines(EstimationLines estnLines);

}
