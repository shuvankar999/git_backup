package com.tip.estimation.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.tip.estimation.model.EstimationLines;

@FunctionalInterface
public interface FetchEstimationLinesService {
	public EstimationLines fetchEstLines(@RequestBody EstimationLines estnLines);

}
