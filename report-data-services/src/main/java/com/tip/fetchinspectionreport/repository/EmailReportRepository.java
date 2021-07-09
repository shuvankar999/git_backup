package com.tip.fetchinspectionreport.repository;

import java.util.Map;

import com.tip.fetchinspectionreport.model.EmailReportRequest;

@FunctionalInterface
public interface EmailReportRepository {

	public Map<String, Object> sendEmail(EmailReportRequest emailReportRequest);

}
