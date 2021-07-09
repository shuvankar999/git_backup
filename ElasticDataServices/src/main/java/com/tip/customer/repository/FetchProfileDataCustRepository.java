package com.tip.customer.repository;

import java.util.List;

@FunctionalInterface
public interface FetchProfileDataCustRepository {

	public List<Integer> getDefaultProfileData(String ssoId);
	
}
