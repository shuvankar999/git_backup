package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EstnTyreAttributeRequest;

@FunctionalInterface
public interface FetchEstnTyreAtrbteRepository {
	
	 public Map<String, Object> getTyreAttribute(EstnTyreAttributeRequest estnTyreAttributeRequest);
}
