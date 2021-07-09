package com.tip.estimation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.estimation.model.SaveEstnOtherReadingRequest;
import com.tip.estimation.repository.SaveEstnOtherReadingRepository;
import com.tip.estimation.service.SaveEstnOtherReadingService;

@Service
@Transactional
public class SaveEstnOtherReadingServiceImpl implements SaveEstnOtherReadingService {

	@Autowired
	SaveEstnOtherReadingRepository saveEstnOtherReadingRepository;
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Object saveEstnOtherReading(SaveEstnOtherReadingRequest saveEstnOtherReadingRequest) {

		Map<String, Object> resultMap= saveEstnOtherReadingRepository.saveEstnOtherReading(saveEstnOtherReadingRequest);
		List<Object> tempList = (List<Object>) resultMap.get("#result-set-1");
	
		return tempList.get(0);
	}

}
