package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.ConversionRequest;

@FunctionalInterface
public interface ConvrsnToWpService {

	public Map<String, Object> getConvrsnDetails(ConversionRequest conversionRequest);
	

}
