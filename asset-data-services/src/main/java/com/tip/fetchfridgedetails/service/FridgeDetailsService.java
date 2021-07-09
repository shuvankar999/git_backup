package com.tip.fetchfridgedetails.service;

import java.util.Map;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;

@FunctionalInterface
public interface FridgeDetailsService {

	public Map<String, Object> getFridgeDetails(FridgeDetailsRequest fridgeDetailsRequest);
	
}
