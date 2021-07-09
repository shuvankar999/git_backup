
package com.tip.thirdpartyequip.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.thirdpartyequip.model.ValidLicenseRequest;
import com.tip.thirdpartyequip.model.ValidSerialNrRequest;
import com.tip.thirdpartyequip.repository.ValidateLicenseRepository;
import com.tip.thirdpartyequip.repository.ValidateSerialRepository;
import com.tip.thirdpartyequip.service.ValidateService;

@Service
public class ValidateServiceImpl implements ValidateService {

	@Autowired
	ValidateSerialRepository validateSerialRepository;
	
	@Autowired
	ValidateLicenseRepository validateLicenseRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> validateSerialNr(ValidSerialNrRequest validSerialNrRequest) {

		Map<String, Object> resultMap = validateSerialRepository.validateSerialNr(validSerialNrRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("SerialNrValidation", lst);
			}
		}

		return returnMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> validateLicenseNr(ValidLicenseRequest validLicenseRequest) {

		Map<String, Object> resultMap = validateLicenseRepository.validateLicenseNr(validLicenseRequest);
		Map<String, Object> returnMap = new HashMap();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnMap.put("LicenseValidation", lst);
			}
		}

		return returnMap;
	}

}
