package com.tip.rplanner.repository;

import java.util.Map;

import com.tip.rplanner.model.WpRequest;

@FunctionalInterface
public interface FetchRplanWpDetailsRepository {
	public Map<String, Object> getWpdetails(WpRequest wpRequest);

}
