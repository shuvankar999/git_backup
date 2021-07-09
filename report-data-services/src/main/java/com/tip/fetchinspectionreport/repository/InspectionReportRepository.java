package com.tip.fetchinspectionreport.repository;

import java.util.Map;

import com.tip.fetchinspectionreport.model.InspectionReportRequest;

@FunctionalInterface
public interface InspectionReportRepository {

	public Map<String, Object> getInspectionReport(InspectionReportRequest inspectionReportRequest);

}
