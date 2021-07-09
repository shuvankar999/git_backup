package com.tip.customer.service;

import java.net.UnknownHostException;

import com.tip.customer.model.ArrayOfCustomerData;
import com.tip.equipmentdetails.model.DynamicFilterRequest;

public interface CustomerDynamicFiltersService {

	public ArrayOfCustomerData getPaginatedEstimationData(DynamicFilterRequest dynamicFilterRequest, int from,int size) throws UnknownHostException;

	public Long getTotalCountOfTextSearch(DynamicFilterRequest dynamicFilterRequest) throws UnknownHostException;
}