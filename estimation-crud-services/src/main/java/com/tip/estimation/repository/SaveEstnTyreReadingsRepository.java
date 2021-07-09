package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.SaveEstnTyreRdngRequest;

@FunctionalInterface
public interface SaveEstnTyreReadingsRepository {
	public Map<String, Object> saveTyreReadings(SaveEstnTyreRdngRequest saveEstnTyreRdngRequest);

}
