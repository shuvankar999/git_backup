package com.tip.asset.service;

import com.tip.asset.model.EquipmentDetailsRequest;
import com.tip.asset.model.EquipmentDetailsResponse;;

@FunctionalInterface
public interface FetchEquipmentService {

	public EquipmentDetailsResponse fetchEquipmentDetails(EquipmentDetailsRequest equipmentDetailsRequest);
	
}