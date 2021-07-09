package com.tip.estimationfilter.service;

import com.tip.estimationfilter.model.FetchFilterDetails;
import com.tip.estimationfilter.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface FetchFilterService {

	public FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);

}
