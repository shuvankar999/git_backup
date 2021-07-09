package com.tip.service;

import java.util.Map;

import com.tip.model.SessionRequest;

public interface ValidateSessionService {

	Map<String, Object> getValidateSessionDetails(SessionRequest sessionRequest);

}
