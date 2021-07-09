
package com.tip.thirdpartyequip.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.thirdpartyequip.model.EquipTyreReadingRequest;
import com.tip.thirdpartyequip.repository.FetchEquipTyreReadingRepository;
import com.tip.thirdpartyequip.service.FetchEquipTyreReadingService;

@Service
public class FetchEquipTyreReadingServiceImpl implements FetchEquipTyreReadingService{

	@Autowired
	FetchEquipTyreReadingRepository fetchEquipTyreReadingRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchEquipTyreReading(EquipTyreReadingRequest equipTyreReadingRequest){
		
		Map<String, Object> resultMap = fetchEquipTyreReadingRepository.fetchEquipTyreReading(equipTyreReadingRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()){
            if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	returnMap.put("TyreReadingList", lst);
            }
		}
		
		return returnMap;
	}

}
