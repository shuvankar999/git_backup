package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.ConversionRequest;

@FunctionalInterface
public interface ConvrsnToWpRepository {
	public Map<String, Object> getConvrsnDetails(ConversionRequest conversionRequest);

}
