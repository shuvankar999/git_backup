package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EstRequest;

@FunctionalInterface
public interface FetchIntchRepository {

	Map<String, Object> getIntchDetails(EstRequest estRequest);

}
