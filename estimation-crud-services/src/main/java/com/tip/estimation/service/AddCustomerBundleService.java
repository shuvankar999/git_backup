package com.tip.estimation.service;

import java.util.List;

import com.tip.estimation.model.CustomerBundleObj;

@FunctionalInterface
public interface AddCustomerBundleService {

	public List<CustomerBundleObj> addCustBundle(List<CustomerBundleObj> custBundleRequest);

}
