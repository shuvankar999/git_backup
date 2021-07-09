package com.tip.estimation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.SaveEstnTyreRdngRequest;
import com.tip.estimation.repository.SaveEstnTyreReadingsRepository;
import com.tip.estimation.service.SaveEstnTyreReadingsService;

@Service
@Transactional
public class SaveEstnTyreReadingsServiceImpl implements SaveEstnTyreReadingsService{
	
	@Autowired
	SaveEstnTyreReadingsRepository saveEstnTyreReadingsRepository;

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Map<String, Object> saveTyreReadings(SaveEstnTyreRdngRequest saveEstnTyreRdngRequest) {
		
		return saveEstnTyreReadingsRepository.saveTyreReadings(saveEstnTyreRdngRequest);
	}
}
