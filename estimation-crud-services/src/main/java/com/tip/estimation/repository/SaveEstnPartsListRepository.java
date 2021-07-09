package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.SaveEstnPartDetailsRequest;

@FunctionalInterface
public interface SaveEstnPartsListRepository {

	Map<String, Object> saveEstnPartDetails(SaveEstnPartDetailsRequest saveEstnPartDetailsRequest);

}
