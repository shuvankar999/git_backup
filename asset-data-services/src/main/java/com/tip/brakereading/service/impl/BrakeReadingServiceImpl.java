package com.tip.brakereading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.brakereading.model.BrakeReadingRequest;
import com.tip.brakereading.model.BrakeReadingResponse;
import com.tip.brakereading.repository.BrakeReadingRepository;
import com.tip.brakereading.service.BrakeReadingService;;



@Service
@Transactional
public class BrakeReadingServiceImpl implements BrakeReadingService{

	@Autowired
	BrakeReadingRepository brakeReadingRepository;
	
	@Override
	public BrakeReadingResponse  getBrakeReadingDetails(BrakeReadingRequest brakeReadingRequest)
	{
		
		  return brakeReadingRepository.fetchBrakeReadingDetails(brakeReadingRequest);
	}
	
}
