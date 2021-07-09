package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EnrichHeaderObject;

@FunctionalInterface
public interface SaveSupplementaryRepository {
	public Map<String, Object> saveHeaderSuppl(EnrichHeaderObject enrichHeaderObject);
}
