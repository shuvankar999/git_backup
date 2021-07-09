package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.FetchReadingsRequest;
import com.tip.estimation.repository.FetchEstnReadingsRepository;
import com.tip.estimation.service.FetchEstnReadingsService;

@Service
public class FetchEstnReadingsServiceImpl implements FetchEstnReadingsService {

	@Autowired
	FetchEstnReadingsRepository fetchEstnReadingsReposotiry;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getReadings(FetchReadingsRequest fetchReadingsRequest) {

		Map<String, Object> resultMap = fetchEstnReadingsReposotiry.getReadings(fetchReadingsRequest);
		Map<String, Object> returnMap = new HashMap();

		if (null != resultMap) {

			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("EstnTyreRdngList", lst);
				} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst2 = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("EstnBrakeRdngList", lst2);
				} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst3 = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("EstnAssetRdngList", lst3);
				} else if ("#result-set-4".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst4 = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("EstnOperRdngList", lst4);
				} else if ("#result-set-5".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst5 = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("EstnTailliftRdngList", lst5);
				} else if ("#result-set-6".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst6 = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("EstnFridgeRdngList", lst6);

				}
			}

		}
		return returnMap;
	}
}
