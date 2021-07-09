package com.tip.fetchdynamicmasterdata.repository;

import java.util.Map;

import com.tip.fetchdynamicmasterdata.model.AllProcedureDataResponse;
import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataRequest;

@FunctionalInterface
public interface FetchDynamicMasterDataRepository {

    public Map<String, Object> getDynamicMasterData(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest, AllProcedureDataResponse allProcedureDataResponse);

}
