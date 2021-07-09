package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchEnrichDetailsRequest;

@FunctionalInterface
public interface FetchEstnEnrichDetailsRepository {
	
	public Map<String, Object> fetchEstEnrichDetails(FetchEnrichDetailsRequest fetchEnrichDetailsRequest);

}
