package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchEquipmentRequest;

@FunctionalInterface
public interface FetchCustEquipmentDetailsRepository {
	Map<String, Object> getEquipmentDetails(FetchEquipmentRequest fetchEquipmentRequest);

}
