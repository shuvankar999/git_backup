package com.tip.suppliermasterdata.repository;

import java.util.Map;

import com.tip.suppliermasterdata.model.AllProcedureDataResponse;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;

@FunctionalInterface
public interface FetchDynamicMasterDataRepository {

    public Map<String, Object> getDynamicMasterData(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest, AllProcedureDataResponse allProcedureDataResponse);

}
