package com.tip.supplier.service;

import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.FetchFilterDetails;

public interface SupplierProcessFilterService {

	public EnterpriseSearchData processFilterDetails(FetchFilterDetails filterDetails);
}