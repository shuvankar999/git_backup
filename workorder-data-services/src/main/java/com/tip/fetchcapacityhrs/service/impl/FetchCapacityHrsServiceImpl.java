package com.tip.fetchcapacityhrs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchcapacityhrs.model.CapacityHrsRequest;
import com.tip.fetchcapacityhrs.repository.FetchCapacityHrsRepository;
import com.tip.fetchcapacityhrs.service.FetchCapacityHrsService;

;


@Service
@Transactional
public class FetchCapacityHrsServiceImpl implements FetchCapacityHrsService {

    @Autowired
    FetchCapacityHrsRepository fetchCapacityHrsRepository;

	@Override
	public Map<String, Object> getCapacityHrs(CapacityHrsRequest request) {
		
		return fetchCapacityHrsRepository.getCapcityHrs(request);

	}

   

}
