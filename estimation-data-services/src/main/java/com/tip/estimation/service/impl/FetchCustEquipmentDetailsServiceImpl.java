package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.FetchEquipmentRequest;
import com.tip.estimation.repository.FetchCustEquipmentDetailsRepository;
import com.tip.estimation.service.FetchCustEquipmentDetailsService;

@Service
public class FetchCustEquipmentDetailsServiceImpl implements FetchCustEquipmentDetailsService {

	@Autowired
	FetchCustEquipmentDetailsRepository fetchCustEquipmentDetailsRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getEquipmentDetails(FetchEquipmentRequest fetchEquipmentRequest) {
		Map<String, Object> resultMap = fetchCustEquipmentDetailsRepository.getEquipmentDetails(fetchEquipmentRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("EquipmentList", lst);
			}
		}

		return returnMap;
	}
}
