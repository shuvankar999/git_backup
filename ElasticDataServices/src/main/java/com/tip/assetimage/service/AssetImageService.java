package com.tip.assetimage.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.model.AssetImageResponse;
import com.tip.assetimage.model.DeleteAssetImageRequest;
import com.tip.assetimage.model.DeleteAssetImageResponse;
import com.tip.assetimage.model.UploadAssetImageResponse;

//@FunctionalInterface
public interface AssetImageService {
   
	public AssetImageResponse getAssetDashboardImage(AssetImageRequest assetImageRequest);
	public UploadAssetImageResponse uploadAssetImage(AssetImageRequest assetImageRequest, MultipartFile[] files);
	public DeleteAssetImageResponse deleteAssetImage(@RequestBody DeleteAssetImageRequest deleteImageRequest);
}