package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.SaveEstnBrakeRdngRequest;

@FunctionalInterface
public interface SaveEstnBrakeReadingsRepository {

	Map<String, Object> saveBrakeReadings(SaveEstnBrakeRdngRequest saveEstnBrakeReadingsService);

}
