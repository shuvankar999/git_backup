package com.tip.estimationreport.service;

import com.tip.estimationreport.model.BaseModel;
import com.tip.estimationreport.model.EstnReportRequest;
import com.tip.estimationreport.model.ResponseObject;

@FunctionalInterface
public interface EstimationReportService{

	public ResponseObject getPdf(EstnReportRequest estnReportRequest, BaseModel bm);

}
