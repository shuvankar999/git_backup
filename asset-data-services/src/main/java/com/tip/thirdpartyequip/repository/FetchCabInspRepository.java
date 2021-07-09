package com.tip.thirdpartyequip.repository;

import java.util.Map;

import com.tip.thirdpartyequip.model.EquipmentCabRequest;

@FunctionalInterface
public interface FetchCabInspRepository {

	public Map<String, Object> getCabDetails(EquipmentCabRequest equipmentCabRequest);

}
