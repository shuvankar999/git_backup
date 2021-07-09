package com.tip.inspection.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.inspection.model.AddCiaAssestRequest;
import com.tip.inspection.model.AddCiaAssestResponse;
import com.tip.inspection.repository.CiaAssestRepository;
import com.tip.inspection.service.CiaAssestService;

@Service
@Transactional
public class CiasAssestServiceImpl implements CiaAssestService {

	@Autowired
	CiaAssestRepository ciaAssestRepository;

	@Override
	public AddCiaAssestResponse saveCiaAssest(AddCiaAssestRequest addCiaAssestRequest) {
		AddCiaAssestResponse addCiaAssestResponse = new AddCiaAssestResponse();
		Map<String,Object> resultMap  = ciaAssestRepository.saveCiaAssest(addCiaAssestRequest);
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					for (int i = 0; i < lst.size(); i++) {
						Map<String, Object> respMap = lst.get(i);
						addCiaAssestResponse.setErrorCd((String) respMap.get("errorCd")); 
						addCiaAssestResponse.setCustEquipRef((String) respMap.get("custEquipRef"));	
						addCiaAssestResponse.setCustomerName((String) respMap.get("customerName"));
						addCiaAssestResponse.setEquipmentNr((Integer) respMap.get("equipmentNr"));
					}
				}
			}
		}
		return addCiaAssestResponse;
	}
}