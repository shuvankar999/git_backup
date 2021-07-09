package com.tip.brakereading.service;

import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.model.BrakeReadingResponse;;

@FunctionalInterface
public interface BrakeReadingService {

	public BrakeReadingResponse getBrakeReadingDetails(BrakeReadingRequest brakeReadingRequest);
	
}
