package com.tip.common.service;

import java.net.UnknownHostException;

import com.tip.common.model.FilterRequest;
import com.tip.common.model.FilterResponse;

@FunctionalInterface
public interface DnaSearchFilterService {

	public FilterResponse getFilterList(FilterRequest filterRequest) throws UnknownHostException;

}
