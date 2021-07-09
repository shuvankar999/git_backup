package com.tip.fetchaxle.model;

import java.util.Map;

public class FetchAxleResponse {
	
	private Map<String, Object> fetchAxleMap;

	public Map<String, Object> getFetchAxleMap() {
		return fetchAxleMap;
	}
	
	public void setFetchAxleMap(Map<String, Object> fetchAxleMap) {
		this.fetchAxleMap = fetchAxleMap;
	}
	
	@Override
	public String toString() {
		return "FetchAxleResponse [fetchAxleMap"+ fetchAxleMap + "]";
	}
	
	
}
