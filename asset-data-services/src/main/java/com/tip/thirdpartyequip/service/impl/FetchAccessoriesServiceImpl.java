
package com.tip.thirdpartyequip.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.thirdpartyequip.model.AccessoryRequest;
import com.tip.thirdpartyequip.repository.FetchAccessoriesRepository;
import com.tip.thirdpartyequip.service.FetchAccessoriesService;

@Service
public class FetchAccessoriesServiceImpl implements FetchAccessoriesService {

	@Autowired
	FetchAccessoriesRepository fetchAccessoriesRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> fetchAccessories(AccessoryRequest accessoryRequest) {

		Map<String, Object> resultMap = fetchAccessoriesRepository.fetchAccessories(accessoryRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("AccessoriesList", lst);
			}
		}

		return returnMap;
	}
}
