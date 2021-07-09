package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchAdminFeesRequest;
@FunctionalInterface
public interface FetchAdminFeesRepository {

	public Map<String, Object> fetchAdminFees(FetchAdminFeesRequest fetchAdminFeesRequest);

}
