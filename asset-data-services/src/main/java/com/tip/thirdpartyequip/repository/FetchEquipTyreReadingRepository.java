package com.tip.thirdpartyequip.repository;

import java.util.Map;

import com.tip.thirdpartyequip.model.EquipTyreReadingRequest;

@FunctionalInterface
public interface FetchEquipTyreReadingRepository {

	/**
	 * @param equipmentReadingRequest
	 * @return
	 */
	public Map<String, Object> fetchEquipTyreReading(EquipTyreReadingRequest equipTyreReadingRequest);

}
