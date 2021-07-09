package com.tip.saveequipment.repository;

import java.util.Map;

import com.tip.saveequipment.model.SaveEquipStaticRequest;

/**
 * @author Shuvankar Paul
 * Created on Dec 19, 2017
 * 
 */
@FunctionalInterface
public interface SaveEquipStaticRepository {

	/**
	 * @param saveEquipStaticRequest
	 * @param appCd
	 * @param ssoId
	 * @return
	 */
	Map<String, Object> saveEquipmentStatic(SaveEquipStaticRequest saveEquipStaticRequest, String appCd, String ssoId);

}
