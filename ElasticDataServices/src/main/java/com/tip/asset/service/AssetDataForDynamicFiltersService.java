package com.tip.asset.service;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.AssetSearchResponse;
import com.tip.equipmentdetails.model.DynamicFilterAssetSpec;
import com.tip.equipmentdetails.model.DynamicFilterRequest;
import com.tip.equipmentdetails.model.ListOfUnit;

public interface AssetDataForDynamicFiltersService {

	public Map<Integer, AssetSearchResponse> dynamicFilterAsset(DynamicFilterRequest dynamicFilterRequest,
			DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit, ArrayOfAssetData arrayOfAssetData)
			throws UnknownHostException;

	public Map<Integer, AssetSearchResponse> dynamicFilterAsset(DynamicFilterRequest dynamicFilterRequest,
			DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit, ArrayOfAssetData arrayOfAssetData,
			Integer from, Integer size) throws UnknownHostException;

	public List<AssetSearchResponse> dynamicFilterAssetSpecs(DynamicFilterRequest dynamicFilterRequest,
			DynamicFilterAssetSpec dynamicFilterAssetSpec, ListOfUnit listOfUnit,
			Map<Integer, AssetSearchResponse> assetDataMap) throws UnknownHostException;
}