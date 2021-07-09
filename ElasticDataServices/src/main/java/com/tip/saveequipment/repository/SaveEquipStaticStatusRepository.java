package com.tip.saveequipment.repository;

import java.util.Map;

import com.tip.saveequipment.model.SaveEquipStaticRequest;

@FunctionalInterface
public interface SaveEquipStaticStatusRepository {

	/**
	 * @param saveEquipStaticRequest
	 * @param appCd
	 * @param ssoId
	 * @return
	 */
	Map<String, Object> saveEquipmentStaticStatus(SaveEquipStaticRequest saveEquipStaticRequest, String appCd, String ssoId);

}
