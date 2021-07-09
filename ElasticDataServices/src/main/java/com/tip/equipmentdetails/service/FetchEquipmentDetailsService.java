package com.tip.equipmentdetails.service;

import com.tip.equipmentdetails.model.FetchEquipDetailsRequest;
import com.tip.equipmentdetails.model.FetchEquipDetailsResponse;

/**
 * @author Akanksha
 * 
 */
@FunctionalInterface
public interface FetchEquipmentDetailsService {

	FetchEquipDetailsResponse getEquipmentDetils(FetchEquipDetailsRequest fetchEquipDetailsRequest,boolean refreshFlag);

}
