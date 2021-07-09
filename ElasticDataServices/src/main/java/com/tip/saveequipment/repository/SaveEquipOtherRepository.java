package com.tip.saveequipment.repository;

import java.util.Map;

import com.tip.saveequipment.model.SaveEquipOtherRequest;

/**
 * @author Shuvankar Paul
 * Created on Dec 18, 2017
 * 
 */
public interface SaveEquipOtherRepository {

	/**
	 * @param saveEquipOtherRequest
	 * @param appCd
	 * @param ssoId
	 * @return
	 */
	Map<String, Object> saveEquipOther(SaveEquipOtherRequest saveEquipOtherRequest, String appCd, String ssoId);

}
