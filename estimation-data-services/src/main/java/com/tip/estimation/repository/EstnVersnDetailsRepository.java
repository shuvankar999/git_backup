package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.VersionDetailsRequest;

@FunctionalInterface
public interface EstnVersnDetailsRepository {
	
	public Map<String, Object> getVersnDetails(VersionDetailsRequest versionDetailsRequest);
}
