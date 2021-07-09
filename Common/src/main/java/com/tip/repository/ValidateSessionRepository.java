package com.tip.repository;

import java.util.Map;

import com.tip.model.SessionRequest;

public interface ValidateSessionRepository {

	public Map<String, Object> getValidateSessionDetails(SessionRequest sessionRequest);

}
