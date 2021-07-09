package com.tip.validatesession.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.validatesession.model.SessionDetails;
import com.tip.validatesession.repository.ValidateSessionRepository;
import com.tip.validatesession.service.ValidateSessionService;

@Service
@Transactional
public class ValidateSessionServiceImpl implements ValidateSessionService{

	@Autowired
	ValidateSessionRepository validateSessionRepository;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String validateSessionDetails(SessionDetails sessionDetails)
	{
		List<Object> sessionMessageList = validateSessionRepository.validateSessionDetails(sessionDetails);
		String sessionMessage = null;
		if (sessionMessageList != null) {
			
			Iterator sessionMessageListIterator = sessionMessageList.iterator();
			while (sessionMessageListIterator.hasNext()) {
				Map<String, Object> sessionMessageListIteratorMap = (Map<String, Object>) sessionMessageListIterator.next();
				sessionMessage = (String) sessionMessageListIteratorMap.get("Session_Msg");
			}
		}
		return sessionMessage;
	}
}