package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.FetchReadingsRequest;

@FunctionalInterface
public interface FetchEstnReadingsService {

	public Map<String, Object> getReadings(FetchReadingsRequest fetchReadingsRequest);

}
