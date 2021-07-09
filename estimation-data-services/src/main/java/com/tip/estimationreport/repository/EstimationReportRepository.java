package com.tip.estimationreport.repository;

import java.util.Map;

import com.tip.estimationreport.model.EstnReportRequest;

@FunctionalInterface
public interface EstimationReportRepository {

	public Map<String, Object> getReportData(EstnReportRequest estnReportRequest);

}
