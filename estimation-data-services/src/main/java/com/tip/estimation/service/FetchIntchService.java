package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.EstRequest;

@FunctionalInterface
public interface FetchIntchService {

	public Map<String, Object> getIntchDetails(EstRequest estRequest);

}
