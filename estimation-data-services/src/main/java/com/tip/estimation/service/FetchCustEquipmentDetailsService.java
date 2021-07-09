package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.FetchEquipmentRequest;

@FunctionalInterface
public interface FetchCustEquipmentDetailsService {

	Map<String, Object> getEquipmentDetails(FetchEquipmentRequest fetchEquipmentRequest);

}
