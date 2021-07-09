package com.tip.equipmentdetails.repository;

import com.tip.equipmentdetails.model.MultiCopyResponse;

@FunctionalInterface
public interface SaveMultipleEquipAllRepository {

	public MultiCopyResponse updateAll(Integer sourceEquipmentNr, String[] finalStringArray);
}