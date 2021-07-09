package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.SaveEstnOtherReadingRequest;

@FunctionalInterface
public interface SaveEstnOtherReadingRepository {
	public Map<String, Object> saveEstnOtherReading(SaveEstnOtherReadingRequest saveEstnOtherReadingRequest);

}
