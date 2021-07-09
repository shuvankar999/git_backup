package com.tip.fetchcapacityhrs.service;

import java.util.Map;

import com.tip.fetchcapacityhrs.model.CapacityHrsRequest;

@FunctionalInterface
public interface FetchCapacityHrsService {

	public Map<String, Object> getCapacityHrs(CapacityHrsRequest request);
}
