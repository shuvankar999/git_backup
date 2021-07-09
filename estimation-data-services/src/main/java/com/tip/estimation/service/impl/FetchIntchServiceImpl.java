package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.EstRequest;
import com.tip.estimation.repository.FetchIntchRepository;
import com.tip.estimation.service.FetchIntchService;

@Service
@Transactional
public class FetchIntchServiceImpl implements FetchIntchService {
	
	@Autowired
	FetchIntchRepository fetchIntchRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getIntchDetails(EstRequest estRequest) {

		Map<String, Object> resultMap = fetchIntchRepository.getIntchDetails(estRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("IntchPopupList", lst);
			}
		}
		
		return returnMap;
	}	

}
