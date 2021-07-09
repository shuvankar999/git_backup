package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchEstnConsumablesRequest;

@FunctionalInterface
public interface FetchEstnConsumablesDetailsRepository {
	
	public Map<String, Object> getConsumablesDetails(FetchEstnConsumablesRequest fetchEstnConsumablesRequest);

}
