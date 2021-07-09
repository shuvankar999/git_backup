package com.tip.asset.service;

import java.net.UnknownHostException;
import java.util.Map;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.equipmentdetails.model.DownloadAssetDetailsRequest;

public interface AssetDataService {
   
	ArrayOfAssetData getAssetData(Map<String, String> fields) throws UnknownHostException;
	public ArrayOfAssetData getPaginatedAssetData(Map<String, String> fields,int from,int size) throws UnknownHostException;
	Long getAssetDataCount(Map<String, String> fields) throws UnknownHostException;
	ArrayOfAssetData assetDataTextSearch(String text) throws UnknownHostException;
	ArrayOfAssetData getAssetDetailForExcel(DownloadAssetDetailsRequest downloadAssetDetailsRequest) throws UnknownHostException;
	public Long assetDataTextSearchCount(String text) throws UnknownHostException;
	public ArrayOfAssetData dynamicFilterAsset() throws UnknownHostException;
}