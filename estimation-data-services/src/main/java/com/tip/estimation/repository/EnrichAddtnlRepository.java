package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EnrichAddtionalRequest;

@FunctionalInterface
public interface EnrichAddtnlRepository {
	
	public Map<String, Object> fetchAddtnlEnrichDetails(EnrichAddtionalRequest enrichAddtionalRequest);

}
