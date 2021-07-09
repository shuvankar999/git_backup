package com.tip.estimationfilter.repository;

import java.util.Map;

@FunctionalInterface
public interface FetchProfileDataRepository {

	public Map<String, Object> fetchProfileData(String ssoId);
}