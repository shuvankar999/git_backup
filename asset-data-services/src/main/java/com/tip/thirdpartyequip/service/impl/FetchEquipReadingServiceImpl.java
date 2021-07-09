
package com.tip.thirdpartyequip.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.thirdpartyequip.model.EquipmentReadingRequest;
import com.tip.thirdpartyequip.repository.FetchEquipReadingRepository;
import com.tip.thirdpartyequip.service.FetchEquipReadingService;

@Service
public class FetchEquipReadingServiceImpl implements FetchEquipReadingService {

	@Autowired
	FetchEquipReadingRepository fetchEquipReadingRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchEquipReading(EquipmentReadingRequest equipmentReadingRequest) {
		
		Map<String, Object> resultMap = fetchEquipReadingRepository.fetchEquipReading(equipmentReadingRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()){
            if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	returnMap.put("MeterObjectList", lst);
            }else if(("#result-set-2").equalsIgnoreCase(entry.getKey())){
            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	returnMap.put("FuelObjectList", lst);
            }
		}
		
		return returnMap;
	}

}
