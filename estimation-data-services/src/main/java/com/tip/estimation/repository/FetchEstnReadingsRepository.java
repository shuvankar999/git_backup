package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchReadingsRequest;

@FunctionalInterface
public interface FetchEstnReadingsRepository {
	public Map<String, Object> getReadings(FetchReadingsRequest fetchReadingsRequest);

}
