package com.tip.asset.repository;

import java.util.Map;

import com.tip.asset.model.EquipmentDetailsRequest;

@FunctionalInterface
public interface FetchEquipmentRepository {

	public Map<String, Object> fetchEquipmentDetails(EquipmentDetailsRequest equipmentDetailsRequest);
}