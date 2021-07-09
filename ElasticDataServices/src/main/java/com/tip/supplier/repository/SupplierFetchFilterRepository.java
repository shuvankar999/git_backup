package com.tip.supplier.repository;

import java.util.Map;

import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface SupplierFetchFilterRepository {

	Map<String, Object> getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);

}
