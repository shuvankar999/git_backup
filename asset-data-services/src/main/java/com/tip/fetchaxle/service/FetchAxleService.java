package com.tip.fetchaxle.service;

import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.model.FetchAxleResponse;;

@FunctionalInterface
public interface FetchAxleService {

	public FetchAxleResponse getNoOfAxlesForAsset(FetchAxleRequest fetchAxleRequest);
	
}
