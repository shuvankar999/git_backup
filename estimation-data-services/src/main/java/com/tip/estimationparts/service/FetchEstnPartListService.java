package com.tip.estimationparts.service;

import java.net.UnknownHostException;

import com.tip.estimationparts.model.PartListResponse;
import com.tip.estimationparts.model.PartsRequest;

@FunctionalInterface
public interface FetchEstnPartListService {

	public PartListResponse getFilterPartsList(PartsRequest partsRequest) throws UnknownHostException;



}
 