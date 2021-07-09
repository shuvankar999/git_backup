package com.tip.assetreading.service;

import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.model.AssetReadingResponse;;

@FunctionalInterface
public interface AssetReadingService {

	public AssetReadingResponse getAssetReadingDetails(AssetReadingRequest assetReadingRequest);
	
}
