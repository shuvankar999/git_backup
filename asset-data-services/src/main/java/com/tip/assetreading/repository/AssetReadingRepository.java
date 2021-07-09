package com.tip.assetreading.repository;

import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.model.AssetReadingResponse;

@FunctionalInterface
public interface AssetReadingRepository {

	public AssetReadingResponse  fetchAssetReadingDetails(AssetReadingRequest assetReadingRequest);
}
