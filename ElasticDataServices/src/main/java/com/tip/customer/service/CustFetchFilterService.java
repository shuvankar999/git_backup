package com.tip.customer.service;

import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface CustFetchFilterService {

	public FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);

}
