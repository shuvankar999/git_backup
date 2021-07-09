package com.tip.fetchcapacityhrs.repository;

import java.util.Map;

import com.tip.fetchcapacityhrs.model.CapacityHrsRequest;

@FunctionalInterface
public interface FetchCapacityHrsRepository {
	
	public Map<String, Object> getCapcityHrs(CapacityHrsRequest request);

}
