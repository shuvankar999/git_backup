package com.tip.fetchdynamicmasterdata.service;

import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.fetchdynamicmasterdata.model.MasterDataDynamicResponse;

@FunctionalInterface
public interface FetchDynamicMasterDataService {

	public MasterDataDynamicResponse getAllProcedures(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest);
}