package com.tip.thirdpartyequip.service;

import java.util.Map;

import com.tip.thirdpartyequip.model.EquipmentReadingRequest;

public interface FetchEquipReadingService {

	public Map<String, Object> fetchEquipReading(EquipmentReadingRequest equipmentReadingRequest);

}
