package com.tip.brakereading.repository;

import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.model.BrakeReadingResponse;

@FunctionalInterface
public interface BrakeReadingRepository {

	public BrakeReadingResponse  fetchBrakeReadingDetails(BrakeReadingRequest brakeReadingRequest);
}
