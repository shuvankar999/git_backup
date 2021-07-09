package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.SaveEstnBrakeRdngRequest;

@FunctionalInterface
public interface SaveEstnBrakeReadingsService {
	public Map<String, Object> saveBrakeReadings(SaveEstnBrakeRdngRequest saveEstnBrakeReadingsService);

}
