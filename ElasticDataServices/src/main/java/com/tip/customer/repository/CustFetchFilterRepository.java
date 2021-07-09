package com.tip.customer.repository;

import java.util.Map;

import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface CustFetchFilterRepository {

	Map<String, Object> getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);

}
