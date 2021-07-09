package com.tip.saveequipment.service;

import java.util.List;

import com.tip.saveequipment.model.BaseResponse;
import com.tip.saveequipment.model.SaveEquipDetailsRequest;

public interface SaveEquipStaticStatusSerivce {

	/**
	 * @param saveEquipDetailsRequest
	 * @param appCd
	 * @param ssoId
	 * @return
	 */
	List<BaseResponse> saveEquipmentStaticStatus(SaveEquipDetailsRequest saveEquipDetailsRequest, String appCd, String ssoId);

}
