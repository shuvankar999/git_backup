package com.tip.assetimage.repository;

import com.tip.assetimage.model.AssetImageRequest;

@FunctionalInterface
public interface AssetImageLocationRepository {

	public String getImageLocation(AssetImageRequest assetImageRequest);
}