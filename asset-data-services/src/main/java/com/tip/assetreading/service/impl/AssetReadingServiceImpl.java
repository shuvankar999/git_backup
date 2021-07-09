package com.tip.assetreading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.assetreading.model.AssetReadingRequest;
import com.tip.assetreading.model.AssetReadingResponse;
import com.tip.assetreading.repository.AssetReadingRepository;
import com.tip.assetreading.service.AssetReadingService;;



@Service
@Transactional
public class AssetReadingServiceImpl implements AssetReadingService{

	@Autowired
	AssetReadingRepository assetReadingRepository;
	
	@Override
	public AssetReadingResponse  getAssetReadingDetails(AssetReadingRequest assetReadingRequest)
	{
		
		  return assetReadingRepository.fetchAssetReadingDetails(assetReadingRequest);
	}
	
}
