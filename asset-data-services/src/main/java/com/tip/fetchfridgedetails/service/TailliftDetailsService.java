package com.tip.fetchfridgedetails.service;

import java.util.Map;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;

@FunctionalInterface
public interface TailliftDetailsService {

	public Map<String, Object> getTailliftDetails(FridgeDetailsRequest fridgeDetailsRequest);
	
}
