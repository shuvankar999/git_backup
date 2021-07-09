package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchCustBundleRequest;

public interface FetchCustBundleRepository {
	public Map<String, Object> getCustBundle(FetchCustBundleRequest fetchCustBundleRequest);

}
