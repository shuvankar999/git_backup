package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.CustomerSearchRequest;

@FunctionalInterface
public interface CustomerSearchService {

	public Map<String, Object> getCustomer(CustomerSearchRequest customerSearchRequest);

}
