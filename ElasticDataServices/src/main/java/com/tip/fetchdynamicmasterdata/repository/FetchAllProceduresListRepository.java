package com.tip.fetchdynamicmasterdata.repository;

import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataResponse;

@FunctionalInterface
public interface FetchAllProceduresListRepository {

    public FetchDynamicMasterDataResponse getAllProcedures(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest);

}
