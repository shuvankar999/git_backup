package com.tip.fetchwpextrahrs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchwpextrahrs.model.ExtraHrsRequest;
import com.tip.fetchwpextrahrs.repository.FetchWpExtraHrsRepository;
import com.tip.fetchwpextrahrs.service.FetchWpExtraHrsService;

@Service
@Transactional
public class FetchWpExtraHrsServiceImpl implements FetchWpExtraHrsService{

	@Autowired
	FetchWpExtraHrsRepository fetchWpExtraHrsRepository;
	
	@Override
	public Map<String, Object> getWpExtraHrs(ExtraHrsRequest extraHrsRequest) {
		
		return fetchWpExtraHrsRepository.getWpExtraHrs(extraHrsRequest);
	}

}
