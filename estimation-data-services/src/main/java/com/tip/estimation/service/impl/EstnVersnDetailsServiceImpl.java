package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.VersionDetailsRequest;
import com.tip.estimation.repository.EstnVersnDetailsRepository;
import com.tip.estimation.service.EstnVersnDetailsService;

	@Service
	public class EstnVersnDetailsServiceImpl implements EstnVersnDetailsService{
		
		@Autowired
		EstnVersnDetailsRepository estnVersnDetailsRespository;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Map<String, Object> getVersnDetails(VersionDetailsRequest versionDetailsRequest) {
		
			Map<String, Object> resultMap = estnVersnDetailsRespository.getVersnDetails(versionDetailsRequest);
			Map<String, Object> returnMap = new HashMap();
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						returnMap.put("EstnVersionDetails", lst);
					}
				}

				return returnMap;
		}
	}
