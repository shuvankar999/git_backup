package com.tip.assetimage.repository;

import com.tip.assetimage.model.AssetImageAccRequest;

@FunctionalInterface
public interface AssetImageAccRepository {

	public boolean updateImageLoc(AssetImageAccRequest assetImageAccRequest);
}