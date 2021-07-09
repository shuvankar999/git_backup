package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.EstnTyreAttributeRequest;
import com.tip.estimation.repository.FetchEstnTyreAtrbteRepository;
import com.tip.estimation.service.FetchEstnTyreAtrbteService;

	@Service
	public class FetchEstnTyreAtrbteServiceImpl implements FetchEstnTyreAtrbteService{
		
		@Autowired
		FetchEstnTyreAtrbteRepository fetchEstnTyreAtrbteRepository;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> getTyreAttribute(EstnTyreAttributeRequest estnTyreAttributeRequest) {
		Map<String, Object> resultMap = fetchEstnTyreAtrbteRepository.getTyreAttribute(estnTyreAttributeRequest);
		Map<String, Object> returnMap = new HashMap();

		if (null != resultMap) {

			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("TyreSpeclist", lst);
				} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst2 = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("TyreServicelist", lst2);
				}

			}
		}
		return returnMap;
	}
}
