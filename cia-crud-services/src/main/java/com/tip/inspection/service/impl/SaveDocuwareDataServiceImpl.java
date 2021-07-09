package com.tip.inspection.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.inspection.model.CiaInspectionDocuware;
import com.tip.inspection.repository.DocumentDetailsRepository;
import com.tip.inspection.service.SaveDocuwareDataService;

@Service
public class SaveDocuwareDataServiceImpl implements SaveDocuwareDataService{
	@Autowired
	DocumentDetailsRepository docuwareDetailsRepository;
	
	@Override
	public String insertDocumentData(CiaInspectionDocuware docInput) {
			String errorCode = null;
			Map<String, Object> resultMap = docuwareDetailsRepository.saveDetails(docInput);
			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> respMap = lst.get(i);
							if(respMap.get("error_Cd")!= null){
								errorCode = (String) respMap.get("error_Cd");
							}
						}
					}
				}
			}
			return errorCode;
		}
	}

