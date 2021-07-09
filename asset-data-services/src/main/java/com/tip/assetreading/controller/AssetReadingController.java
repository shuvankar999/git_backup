package com.tip.assetreading.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.model.AssetReadingResponse;
import com.tip.assetreading.service.AssetReadingService;

@RestController
@RequestMapping("/service/asset-data-service/2.0/")
public class AssetReadingController {
	
	@Autowired
	AssetReadingService assetReadingService;
	
	@RequestMapping(value = "fetchAssetReading", method = RequestMethod.POST)
	@ResponseBody
	public AssetReadingResponse getAssetReading(@Valid @RequestBody AssetReadingRequest assetReadingRequest) {		
		return assetReadingService.getAssetReadingDetails(assetReadingRequest);
	}
}