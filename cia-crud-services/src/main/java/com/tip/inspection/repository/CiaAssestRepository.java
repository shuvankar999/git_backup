package com.tip.inspection.repository;

import java.util.Map;

import com.tip.inspection.model.AddCiaAssestRequest;

@FunctionalInterface
public interface CiaAssestRepository {

	public Map<String, Object> saveCiaAssest(AddCiaAssestRequest addCiaAssestRequest);

}
