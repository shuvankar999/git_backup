package com.tip.fetchfridgedetails.repository;

import java.util.Map;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;

@FunctionalInterface
public interface FridgeDetailsRepository {

	public Map<String, Object> getFridgeDetails(FridgeDetailsRequest fridgeDetailsRequest);
}
