package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.EquipSearchRequest;
import com.tip.estimation.repository.EquipSearchRepository;
import com.tip.estimation.service.EquipSearchService;

@Service
@Transactional
public class EquipSearchServiceImpl implements EquipSearchService {
	
	@Autowired
	EquipSearchRepository equipSearchRepository;

	@Override
	public Map<String, Object> getEquipment(EquipSearchRequest equipSearchRequest) {

		Map<String, Object> resultMap = equipSearchRepository.getEquipment(equipSearchRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("equipList", lst);
			}
		}
		
		return returnMap;
	}	

}
