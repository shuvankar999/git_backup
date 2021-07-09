package com.tip.tyrereading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.model.TyreReadingResponse;
import com.tip.tyrereading.repository.TyreReadingRepository;
import com.tip.tyrereading.service.TyreReadingService;;



@Service
@Transactional
public class TyreReadingServiceImpl implements TyreReadingService {

	@Autowired
	TyreReadingRepository tyreReadingRepository;
	
	@Override
	public TyreReadingResponse  getTyreReadingDetails(TyreReadingRequest tyreReadingRequest)
	{
		
		  return tyreReadingRepository.fetchTyreReadingDetails(tyreReadingRequest);
	}
	
}
