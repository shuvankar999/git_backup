package com.tip.suppliermasterdata.service;

import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.MasterDataDynamicResponse;

@FunctionalInterface
public interface FetchDynamicMasterDataService {

	public MasterDataDynamicResponse getAllProcedures(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest);
}