package com.tip.fetchinspectionreport.service;

import com.tip.fetchinspectionreport.model.InspectionReportRequest;
import com.tip.fetchinspectionreport.model.ResponseObject;

@FunctionalInterface
public interface InspectionReportService {

	public ResponseObject getInspectionReport(InspectionReportRequest inspectionReportRequest);

}
