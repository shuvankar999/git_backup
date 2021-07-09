package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.ConversionRequest;
import com.tip.estimation.repository.ConvrsnToWpRepository;
import com.tip.estimation.service.ConvrsnToWpService;


	@Service
	@Transactional
	public class ConvrsnToWpServiceImpl implements ConvrsnToWpService{
		
		@Autowired
		ConvrsnToWpRepository convrsnToWpRepository;

		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> getConvrsnDetails(ConversionRequest conversionRequest) {
			
			Map<String, Object> resultMap = convrsnToWpRepository.getConvrsnDetails(conversionRequest);
			Map<String, Object> returnMap = new HashMap();
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("WpDetailslist", lst);
				}
			}
			
			return returnMap;
		}
	}
