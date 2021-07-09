package com.tip.estimation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.SaveEstnPartDetailsRequest;
import com.tip.estimation.repository.SaveEstnPartsListRepository;
import com.tip.estimation.service.SaveEstnPartsListService;

	@Service
	@Transactional
	public class SaveEstnPartsListServiceImpl implements SaveEstnPartsListService{
		
		@Autowired
		SaveEstnPartsListRepository saveEstnPartsListRepository;

		@Override
		public Map<String, Object> saveEstnPartDetails(SaveEstnPartDetailsRequest saveEstnPartDetailsRequest) {
			
			return saveEstnPartsListRepository.saveEstnPartDetails(saveEstnPartDetailsRequest);
		}
	
	}
