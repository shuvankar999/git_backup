package com.tip.estimationmailreport.service;

import java.util.Map;

import com.tip.estimationmailreport.model.EstnEmailReportRequest;

	@FunctionalInterface
	public interface EstnEmailReportService {
	
		Map<String, Object> sendEmail(EstnEmailReportRequest estnEmailReportRequest);
	
	}
