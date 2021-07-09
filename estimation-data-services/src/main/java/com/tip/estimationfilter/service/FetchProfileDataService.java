package com.tip.estimationfilter.service;

import com.tip.estimationfilter.model.DynamicFilterRequest;

@FunctionalInterface
public interface FetchProfileDataService {

	public void fetchProfileData(String ssoId, DynamicFilterRequest dynamicFilterRequest);
}