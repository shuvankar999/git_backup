package com.tip.estimation.service;

import com.tip.estimation.model.FetchAdminFeesRequest;
import com.tip.estimation.model.FetchAdminFeesResponse;
@FunctionalInterface
public interface FetchAdminFeesService {

	public FetchAdminFeesResponse fetchAdminFees(FetchAdminFeesRequest fetchAdminFeesRequest);

}
