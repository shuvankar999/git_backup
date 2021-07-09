package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.ValidatePartRequest;
import com.tip.estimation.repository.ValidateEstnPartRepository;
import com.tip.estimation.service.ValidateEstnPartService;

@Service
public class ValidateEstnPartServiceImpl implements ValidateEstnPartService{
	
	@Autowired
	ValidateEstnPartRepository validateEstnPartRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> validatePart(ValidatePartRequest validatePartRequest) {
		Map<String, Object> resultMap = validateEstnPartRepository.validatePart(validatePartRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("EstnPartListResponse", lst);
			}
		}
		
		return returnMap;
	}	

	}


