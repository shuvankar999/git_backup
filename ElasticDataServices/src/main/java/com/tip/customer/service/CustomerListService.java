package com.tip.customer.service;

import java.util.Map;

import com.tip.customer.model.CustomerListRequest;

@FunctionalInterface
public interface CustomerListService {

	public Map<String, Object> getCustomerList(CustomerListRequest customerListRequest);

}
