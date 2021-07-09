package com.tip.estimation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.EstnInspectionRequest;
import com.tip.estimation.repository.EstnInspectionRepository;
import com.tip.estimation.service.EstnInspectionService;

@Service
@Transactional
public class EstnInspectionServiceImpl implements EstnInspectionService{
	
	@Autowired
	EstnInspectionRepository estnInspectionRepository;

	
	@Override
	public Map<String, Object> getInspectionDetails(EstnInspectionRequest estnInspectionRequest) {
		
		return estnInspectionRepository.getInspectionDetails(estnInspectionRequest);
	}

}
