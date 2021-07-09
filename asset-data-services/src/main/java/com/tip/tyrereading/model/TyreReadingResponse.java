package com.tip.tyrereading.model;

import java.util.Map;

public class TyreReadingResponse {
	
	private Map<String, Object> tyreReadingMap;

	public Map<String, Object> getTyreReadingMap() {
		return tyreReadingMap;
	}
	
	public void setTyreReadingMap(Map<String, Object> tyreReadingMap) {
		this.tyreReadingMap = tyreReadingMap;
	}
	
	@Override
	public String toString() {
		return "TyreReadingResponse [tyreReadingMap"+ tyreReadingMap + "]";
	}
	
	
}
