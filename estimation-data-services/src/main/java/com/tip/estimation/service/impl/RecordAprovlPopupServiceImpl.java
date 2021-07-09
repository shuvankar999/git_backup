package com.tip.estimation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.RecordAprovlPopupRequest;
import com.tip.estimation.repository.RecordAprovlPopupRepository;
import com.tip.estimation.service.RecordAprovlPopupService;


	@Service
	public class RecordAprovlPopupServiceImpl implements RecordAprovlPopupService{
		
		@Autowired
		RecordAprovlPopupRepository recordAprovlPopupRepository;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Map<String, Object> getPopupList(RecordAprovlPopupRequest recordAprovlPopupRequest) {
			
			Map<String, Object> resultMap = recordAprovlPopupRepository.getPopupList(recordAprovlPopupRequest);
			Map<String, Object> returnMap = new HashMap();
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					returnMap.put("ApprovalPopupList", lst);
				}
			}
			
			return returnMap;
		}	
		
	
	}
