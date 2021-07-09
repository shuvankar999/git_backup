package com.tip.estimation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.SaveEstnBrakeRdngRequest;
import com.tip.estimation.repository.SaveEstnBrakeReadingsRepository;
import com.tip.estimation.service.SaveEstnBrakeReadingsService;

@Service
@Transactional
public class SaveEstnBrakeReadingsServiceImpl implements SaveEstnBrakeReadingsService{
	
	@Autowired
	SaveEstnBrakeReadingsRepository saveBrakeTyreReadingsRepository;
	@Override
	public Map<String, Object> saveBrakeReadings(SaveEstnBrakeRdngRequest saveEstnBrakeReadingsService) {
		
		return saveBrakeTyreReadingsRepository.saveBrakeReadings(saveEstnBrakeReadingsService);
	}

}
