package com.tip.assetimage.repository;

import java.io.File;

import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.model.DeleteAssetImageRequest;

public interface UploadAssetImageRepository {

	public String uploadImage(File image,AssetImageRequest assetImageRequest);
	public String deleteImage(DeleteAssetImageRequest deleteImageRequest);
}