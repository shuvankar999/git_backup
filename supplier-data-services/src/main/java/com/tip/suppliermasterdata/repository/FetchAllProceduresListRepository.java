package com.tip.suppliermasterdata.repository;

import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataResponse;

@FunctionalInterface
public interface FetchAllProceduresListRepository {

    public FetchDynamicMasterDataResponse getAllProcedures(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest);

}
