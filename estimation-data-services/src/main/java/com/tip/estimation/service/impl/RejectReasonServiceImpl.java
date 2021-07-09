package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.RejectReasonRequest;
import com.tip.estimation.repository.RejectReasonRepository;
import com.tip.estimation.service.RejectReasonService;

	@Service
	public class RejectReasonServiceImpl implements RejectReasonService{
		
		@Autowired
		RejectReasonRepository rejectReasonRepository;
	
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Map<String, Object> getPopupList(RejectReasonRequest rejectReasonRequest) {
			
			Map<String, Object> resultMap = rejectReasonRepository.getPopupList(rejectReasonRequest);
			Map<String, Object> returnMap = new HashMap();
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("RejectReasonPopup", lst);
				}
			}
			
			return returnMap;
		}	
		
		
	
	}
