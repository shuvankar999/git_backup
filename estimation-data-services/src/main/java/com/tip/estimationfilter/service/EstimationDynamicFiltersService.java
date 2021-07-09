package com.tip.estimationfilter.service;

import java.net.UnknownHostException;

import com.tip.estimationfilter.model.ArrayOfEstimationData;
import com.tip.estimationfilter.model.DynamicFilterRequest;

public interface EstimationDynamicFiltersService {

	public ArrayOfEstimationData getPaginatedEstimationData(DynamicFilterRequest dynamicFilterRequest, int from,int size) throws UnknownHostException;

	public Long getTotalCountOfTextSearch(DynamicFilterRequest dynamicFilterRequest) throws UnknownHostException;
}