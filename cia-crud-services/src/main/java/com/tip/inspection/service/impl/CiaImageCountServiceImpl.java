package com.tip.inspection.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.inspection.repository.CiaImageCountRepository;
import com.tip.inspection.service.CiaImageCountService;

@Service
@Transactional
public class CiaImageCountServiceImpl implements CiaImageCountService {

	@Autowired
	CiaImageCountRepository ciaImageCountRepository;

	@SuppressWarnings("unchecked")
	@Override
	public int getImageCount(String inspId, String inspType) {
		int count=0;
		//BigDecimal id = new BigDecimal(inspId);
		Map<String, Object> resultMap = ciaImageCountRepository.fetchImageCount(inspId,inspType);
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					for (int i = 0; i < lst.size(); i++) {
						Map<String, Object> respMap = lst.get(i);
						if(respMap.get("Img_Count")!= null){
							count = (Integer)respMap.get("Img_Count");
						}
					}
				}
			}                   
		}
		return count;
	}


}
