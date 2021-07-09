package com.tip.thirdpartyequip.service;

import java.util.Map;

import com.tip.thirdpartyequip.model.EquipTyreReadingRequest;

@FunctionalInterface
public interface FetchEquipTyreReadingService {

	public Map<String, Object> fetchEquipTyreReading(EquipTyreReadingRequest equipTyreReadingRequest);

}
