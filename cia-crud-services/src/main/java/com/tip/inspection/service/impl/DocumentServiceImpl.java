package com.tip.inspection.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class DocumentServiceImpl 
//implements DocumentService 
{
	
	/*@Autowired
	DocumentDetailsRepository docuwareDetailsRepository;

	@SuppressWarnings("unchecked")
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

	@Override
	public DocuwareDetails getDocuwareDetails(String appCd) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	*/
}
