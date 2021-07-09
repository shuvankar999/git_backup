package com.tip.thirdpartyequip.repository;

import java.util.Map;

import com.tip.thirdpartyequip.model.EquipmentReadingRequest;

public interface FetchEquipReadingRepository {

	/**
	 * @param equipmentReadingRequest
	 * @return
	 */
	public Map<String, Object> fetchEquipReading(EquipmentReadingRequest equipmentReadingRequest);

}
