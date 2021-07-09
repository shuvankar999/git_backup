package com.tip.fetchinspectionreport.service;

import java.util.Map;

import com.tip.fetchinspectionreport.model.EmailReportRequest;

@FunctionalInterface
public interface EmailReportService {

	public Map<String, Object> sendEmail(EmailReportRequest emailReportRequest);

}
