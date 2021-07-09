package com.tip.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.model.SessionRequest;
import com.tip.repository.ValidateSessionRepository;
import com.tip.service.ValidateSessionService;



@Service
@Transactional
public class ValidateSessionServiceImpl implements ValidateSessionService{

	@Autowired
	ValidateSessionRepository validateSessionRepository;
	
	@Override
	public Map<String, Object> getValidateSessionDetails(SessionRequest sessionRequest) {
		
		  return validateSessionRepository.getValidateSessionDetails(sessionRequest);
	}

}