package com.tip.saveequipment.service;

import java.util.Map;

import com.tip.saveequipment.model.SaveEquipDetailsRequest;

/**
 * @author Shuvankar Paul
 * Created on Dec 15, 2017
 * 
 */
@FunctionalInterface
public interface SaveEquipDynamicService {

	public Map<String, Object> saveEquip(SaveEquipDetailsRequest saveEquipDetailsRequest, String appCd, String ssoId);
	
}
