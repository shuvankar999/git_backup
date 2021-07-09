package com.tip.estimationmailreport.repository;

import java.util.Map;

import com.tip.estimationmailreport.model.EstnEmailReportRequest;

	@FunctionalInterface
	public interface EstnEmailReportRepository {
		
		public Map<String, Object> sendEmail(EstnEmailReportRequest estnEmailReportRequest);
	
	}
