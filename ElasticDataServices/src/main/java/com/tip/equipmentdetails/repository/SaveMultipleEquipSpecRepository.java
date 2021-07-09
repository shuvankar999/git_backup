package com.tip.equipmentdetails.repository;

import java.util.Map;

import com.tip.equipmentdetails.model.MultiCopyRequest;
import com.tip.equipmentdetails.model.MultiCopyResponse;

@FunctionalInterface
public interface SaveMultipleEquipSpecRepository {

	public MultiCopyResponse updateMultipleEquip(MultiCopyRequest multiCopyRequest, Map<String, Object> dbColumnMap);
}