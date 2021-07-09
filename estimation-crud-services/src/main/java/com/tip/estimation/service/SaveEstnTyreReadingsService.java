package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.SaveEstnTyreRdngRequest;

@FunctionalInterface
public interface SaveEstnTyreReadingsService {

	Map<String, Object> saveTyreReadings(SaveEstnTyreRdngRequest saveEstnTyreRdngRequest);

}
