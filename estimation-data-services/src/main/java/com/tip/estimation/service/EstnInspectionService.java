package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.EstnInspectionRequest;

@FunctionalInterface
public interface EstnInspectionService {

	Map<String, Object> getInspectionDetails(EstnInspectionRequest estnInspectionRequest);

}
