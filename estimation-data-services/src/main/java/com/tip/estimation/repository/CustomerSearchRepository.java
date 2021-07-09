package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.CustomerSearchRequest;

@FunctionalInterface
public interface CustomerSearchRepository {

	Map<String, Object> getCustomer(CustomerSearchRequest customerSearchRequest);

}
