package com.tip.validatesession.repository;

import java.util.List;

import com.tip.validatesession.model.SessionDetails;

@FunctionalInterface
public interface ValidateSessionRepository {

	public List<Object> validateSessionDetails(SessionDetails sessionDetails);
}