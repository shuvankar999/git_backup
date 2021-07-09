package com.tip.assetreading.model;

import java.util.Map;

public class AssetReadingResponse {
	
	private Map<String, Object> assetReadingMap;

	public Map<String, Object> getAssetReadingMap() {
		return assetReadingMap;
	}
	
	public void setAssetReadingMap(Map<String, Object> assetReadingMap) {
		this.assetReadingMap = assetReadingMap;
	}
	
	@Override
	public String toString() {
		return "AssetReadingResponse [assetReadingMap"+ assetReadingMap + "]";
	}
	
	
}
