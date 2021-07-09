package com.tip.inspection.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.inspection.model.DocuwareDetails;
import com.tip.inspection.repository.GetDocuwareDetailsRepository;
import com.tip.inspection.service.GetDocuwareDetailsService;

@Service
@Transactional
public class GetDocuwareDetailsServiceImpl implements GetDocuwareDetailsService {

	@Autowired
	GetDocuwareDetailsRepository docuwareDetailsRepository;

	@SuppressWarnings("unchecked")
	@Override
	public DocuwareDetails getDocuwareDetails(String appCd) {
		DocuwareDetails docuwareDetails = new DocuwareDetails();
		Map<String, Object> resultMap = docuwareDetailsRepository.fetchDocuwareDetails(appCd);
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					for (int i = 0; i < lst.size(); i++) {
						Map<String, Object> respMap = lst.get(i);
						docuwareDetails.setDocOrg((String) respMap.get("Docu_Org"));
						docuwareDetails.setDocPwd((String) respMap.get("Docu_PWD"));
						docuwareDetails.setDocuCabinate((String) respMap.get("Docu_Cabinate"));
						docuwareDetails.setDocUser((String) respMap.get("Docu_User"));
						docuwareDetails.setDocuwareIp((String) respMap.get("Docu_IP")); 
						docuwareDetails.setDocuwarePort((String) respMap.get("Docu_Port"));
				}
			}
		}
	}
		return docuwareDetails;

	}
}
