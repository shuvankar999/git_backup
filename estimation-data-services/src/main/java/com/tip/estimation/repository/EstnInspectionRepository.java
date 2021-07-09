package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EstnInspectionRequest;

@FunctionalInterface
public interface EstnInspectionRepository {
	
	public Map<String, Object> getInspectionDetails(EstnInspectionRequest estnInspectionRequest);

}
