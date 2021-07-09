package com.tip.fetchaxle.repository;

import com.tip.fetchaxle.model.FetchAxleRequest;
import com.tip.fetchaxle.model.FetchAxleResponse;

@FunctionalInterface
public interface FetchAxleRepository {

	public FetchAxleResponse  fetchNoOfAxleForAsset(FetchAxleRequest fetchAxleRequest);
}
