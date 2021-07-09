package com.tip.equipmentdetails.service;

import com.tip.equipmentdetails.model.DynamicFilterRequest;

@FunctionalInterface
public interface FetchProfileDataService {

	public void fetchProfileData(String ssoId, DynamicFilterRequest dynamicFilterRequest);
}