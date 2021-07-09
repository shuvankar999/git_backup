package com.tip.fetchinspectionreport.repository;

import com.tip.fetchinspectionreport.model.FetchAxleRequest;

@FunctionalInterface
public interface FetchAxleRepository {

	public Object fetchNoOfAxleForAsset(FetchAxleRequest fetchAxleRequest);
}
