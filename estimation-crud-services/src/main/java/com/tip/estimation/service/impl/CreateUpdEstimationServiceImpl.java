package com.tip.estimation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.EstimationRequest;
import com.tip.estimation.repository.CreateUpdEstimationRepository;
import com.tip.estimation.service.CreateUpdEstimationService;

@Service
@Transactional
public class CreateUpdEstimationServiceImpl implements CreateUpdEstimationService {
	
	@Autowired
	CreateUpdEstimationRepository createUpdEstimationRepository;

	@SuppressWarnings("unchecked")
	@Override
	public Object createEstimation(EstimationRequest estimationRequest) {

		Map<String, Object> resultMap = createUpdEstimationRepository.createEstimation(estimationRequest);
		Object objReturn = null;
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				objReturn = lst.get(0);
			}
		}
		return objReturn;
	}	
}
