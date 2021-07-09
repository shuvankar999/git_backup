package com.tip.fetchfridgedetails.repository;

import java.util.Map;

import com.tip.fetchfridgedetails.model.FridgeDetailsRequest;

@FunctionalInterface
public interface TailliftDetailsRepository {

	public Map<String, Object> getTailliftDetails(FridgeDetailsRequest fridgeDetailsRequest);
}
