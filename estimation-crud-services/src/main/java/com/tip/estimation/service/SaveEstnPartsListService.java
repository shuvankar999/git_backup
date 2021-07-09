package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.SaveEstnPartDetailsRequest;

@FunctionalInterface
public interface SaveEstnPartsListService {

	Map<String, Object> saveEstnPartDetails(SaveEstnPartDetailsRequest saveEstnPartDetailsRequest);

}
