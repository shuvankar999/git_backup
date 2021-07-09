package com.tip.assetimage.repository;

import java.util.Map;

import com.tip.assetimage.model.AssetImageRequest;

//@FunctionalInterface
public interface AssetImageRepository {

	//public ResultSet getTipAssetDashboardImage(AssetImageRequest assetImageRequest);
	//public ResultSet getTpAssetDashboardImage(AssetImageRequest assetImageRequest);
	public Map<String, Object> getAssetDashboardImage(AssetImageRequest assetImageRequest);
}