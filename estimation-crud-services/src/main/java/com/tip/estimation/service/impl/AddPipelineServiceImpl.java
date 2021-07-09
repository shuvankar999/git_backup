package com.tip.estimation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.PipelineRequest;
import com.tip.estimation.repository.AddPipelineRepository;
import com.tip.estimation.service.AddPipelineService;

@Service
@Transactional
public class AddPipelineServiceImpl implements AddPipelineService {
	
	@Autowired
	AddPipelineRepository addPipelineRepository;

	@SuppressWarnings("unchecked")
	@Override
	public Object addToPipeline(PipelineRequest pipelineRequest) {

		Map<String, Object> resultMap = addPipelineRepository.addToPipeline(pipelineRequest);
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
