package com.tip.customer.repository;

import java.util.Map;

import com.tip.customer.model.CustomerListRequest;

@FunctionalInterface
public interface CustomerListRepository {
	Map<String, Object> getCustomerList(CustomerListRequest customerListRequest);

}
