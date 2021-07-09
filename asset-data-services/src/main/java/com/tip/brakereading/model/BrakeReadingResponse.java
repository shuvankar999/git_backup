package com.tip.brakereading.model;

import java.util.Map;

public class BrakeReadingResponse {
	
	private Map<String, Object> brakeReadingMap;

	public Map<String, Object> getBrakeReadingMap() {
		return brakeReadingMap;
	}
	
	public void setBrakeReadingMap(Map<String, Object> brakeReadingMap) {
		this.brakeReadingMap = brakeReadingMap;
	}
	
	@Override
	public String toString() {
		return "BrakeReadingResponse [brakeReadingMap"+ brakeReadingMap + "]";
	}
	
	
}
