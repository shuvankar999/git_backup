package com.tip.thirdpartyequip.service;

import com.tip.thirdpartyequip.model.EquipmentCabRequest;
import com.tip.thirdpartyequip.model.EquipmentCabResponse;

@FunctionalInterface
public interface FetchCabInspService {

	public EquipmentCabResponse getCabDetails(EquipmentCabRequest equipmentCabRequest);

}
