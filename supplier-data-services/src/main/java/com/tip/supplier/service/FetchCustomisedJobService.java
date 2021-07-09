package com.tip.supplier.service;

import com.tip.supplier.model.FetchJobRequest;
import com.tip.supplier.model.FetchJobResponse;

@FunctionalInterface
public interface FetchCustomisedJobService {

	public FetchJobResponse fetchCustomisedJob(FetchJobRequest fetchJobRequest);

}
