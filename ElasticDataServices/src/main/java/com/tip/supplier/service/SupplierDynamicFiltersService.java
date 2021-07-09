package com.tip.supplier.service;

import java.net.UnknownHostException;

import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.supplier.model.ArrayOfSupplierData;

public interface SupplierDynamicFiltersService {

	public ArrayOfSupplierData getPaginatedEstimationData(DynamicFilterRequest dynamicFilterRequest, int from,int size) throws UnknownHostException;

	public Long getTotalCountOfTextSearch(DynamicFilterRequest dynamicFilterRequest) throws UnknownHostException;
}