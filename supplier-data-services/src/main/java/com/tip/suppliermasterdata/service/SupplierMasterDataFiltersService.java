package com.tip.suppliermasterdata.service;

import java.net.UnknownHostException;
import java.util.List;

import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.MasterDataDynamicResponse;


public interface SupplierMasterDataFiltersService {

	public void getSupplierElasticData(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest,
			MasterDataDynamicResponse lMasterDataDynamicResponse, List<String> vendorCategoryDescList) throws UnknownHostException;
}