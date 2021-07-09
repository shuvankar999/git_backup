package com.tip.assetimage.service;

import org.springframework.web.multipart.MultipartFile;

import com.tip.assetimage.model.AssetImageAccRequest;

@FunctionalInterface
public interface AssetImageAccService {

	public AssetImageAccRequest uploadAssetImageAcc(AssetImageAccRequest assetImageAccRequest, MultipartFile file);
	
}