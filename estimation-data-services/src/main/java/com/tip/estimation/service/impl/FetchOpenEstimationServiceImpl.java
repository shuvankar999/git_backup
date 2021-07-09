package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EstRequest;
import com.tip.estimation.repository.FetchOpenEstimationRepository;
import com.tip.estimation.service.FetchOpenEstimationService;

@Service
public class FetchOpenEstimationServiceImpl implements FetchOpenEstimationService {

	@Autowired
	FetchOpenEstimationRepository fetchOpenEstimationRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getOpenEstimation(EstRequest estRequest) {
		Map<String, Object> resultMap = fetchOpenEstimationRepository.getOpenEstimation(estRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("openEstnCount", lst.get(0).get("OpenEstnCnt"));
			}else if (("#result-set-2").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("OpenEstnList", lst);
			}
		}

		return returnMap;
	}

}
