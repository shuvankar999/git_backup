package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.FetchEstnPopupDetailsRequest;
import com.tip.estimation.repository.FetchPopupListRepository;
import com.tip.estimation.service.FetchPopupListService;

@Service
@Transactional
public class FetchPopupListServiceImpl implements FetchPopupListService {

	@Autowired
	FetchPopupListRepository fetchPopupListRepository;

	@Override
	public Map<String, Object> getPopupList(FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest) {
		Map<String, Object> resultMap = fetchPopupListRepository.getPopupList(fetchEstnPopupDetailsRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("EstimationPopupList", lst);
			}
		}
		
		return returnMap;
	}	
}