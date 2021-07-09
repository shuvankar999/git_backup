package com.tip.asset.service;

import java.net.UnknownHostException;
import java.util.Map;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.equipmentdetails.model.DownloadAssetDetailsRequest;

public interface ThirdPartyAssetDataService {
   
	ArrayOfAssetData getTpAssetData(Map<String, String> fields) throws UnknownHostException;
	Long getTpAssetDataCount(Map<String, String> fields) throws UnknownHostException;
	ArrayOfAssetData tpAssetDataTextSearch(String text) throws UnknownHostException;
	public ArrayOfAssetData getTpAssetDetailForExcel(DownloadAssetDetailsRequest downloadAssetDetailsRequest) throws UnknownHostException;
	public Long tpAssetDataTextSearchCount(String text) throws UnknownHostException;
}