package com.tip.supplier.repository;

import java.util.Map;

import com.tip.supplier.model.FetchJobRequest;

@FunctionalInterface
public interface FetchCustomisedJobRepository {

	public Map<String, Object> fetchCustomisedJob(FetchJobRequest fetchJobRequest);

}
