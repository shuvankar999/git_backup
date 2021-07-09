package com.tip.equipmentdetails.service;

import com.tip.equipmentdetails.model.MultiCopyRequest;
import com.tip.equipmentdetails.model.MultiCopyResponse;

public interface SaveMultipleEquipSpecService {

	public MultiCopyResponse updateMultipleEquip(MultiCopyRequest multiCopyRequest);
	public MultiCopyResponse updateAll(MultiCopyRequest multiCopyRequest);

}
