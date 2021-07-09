package com.tip.saveequipment.service;

import java.util.List;

import com.tip.saveequipment.model.BaseResponse;
import com.tip.saveequipment.model.SaveEquipDetailsRequest;

/**
 * @author Shuvankar Paul
 * Created on Dec 19, 2017
 * 
 */
public interface SaveEquipStaticSerivce {

	/**
	 * @param saveEquipDetailsRequest
	 * @param appCd
	 * @param ssoId
	 * @return
	 */
	List<BaseResponse> saveEquipmentStatic(SaveEquipDetailsRequest saveEquipDetailsRequest, String appCd, String ssoId);

}
