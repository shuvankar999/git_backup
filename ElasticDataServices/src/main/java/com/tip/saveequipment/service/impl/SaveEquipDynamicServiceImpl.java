package com.tip.saveequipment.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.saveequipment.model.SaveEquipDetailsRequest;
import com.tip.saveequipment.repository.SaveEquipDynamicRepository;
import com.tip.saveequipment.service.SaveEquipDynamicService;

/**
 * @author Shuvankar Paul
 * Created on Dec 15, 2017
 * 
 */
@Service
@Transactional
public class SaveEquipDynamicServiceImpl implements SaveEquipDynamicService{

	@Autowired
	SaveEquipDynamicRepository saveEquipDynamicRepository;
	
	@Override
	public Map<String, Object> saveEquip(SaveEquipDetailsRequest saveEquipDetailsRequest, String appCd, String ssoId) {
		
		return saveEquipDynamicRepository.saveEquip(saveEquipDetailsRequest, appCd, ssoId);
	}

}
