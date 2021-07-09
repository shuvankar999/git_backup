package com.tip.supplier.service;

import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface SupplierFetchFilterService {

	public FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);

}
