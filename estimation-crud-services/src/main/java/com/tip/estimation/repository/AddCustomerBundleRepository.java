package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.CustomerBundleObj;

@FunctionalInterface
public interface AddCustomerBundleRepository {
	public int[] addCustBundle(List<CustomerBundleObj> custBundleRequest);

}
