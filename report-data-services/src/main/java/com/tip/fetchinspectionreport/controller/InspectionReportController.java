package com.tip.fetchinspectionreport.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchinspectionreport.model.InspectionReportRequest;
import com.tip.fetchinspectionreport.model.ResponseObject;
import com.tip.fetchinspectionreport.service.InspectionReportService;

@RestController
@RequestMapping("/service/report-data-service/2.0/")
public class InspectionReportController {

	@Autowired
	InspectionReportService inspectionReportService;
	
	@RequestMapping(value = "InspectionReport", method = RequestMethod.POST, produces = "application/pdf")
	public void download(@Valid @RequestBody InspectionReportRequest inspectionReportRequest, HttpServletResponse response)
					throws IOException {
			ResponseObject responseObject = inspectionReportService.getInspectionReport(inspectionReportRequest);
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline;filename=" + responseObject.getFile().getName());
			IOUtils.copy(responseObject.getInputStream(), response.getOutputStream());
			responseObject.getOutputStream().close();
			responseObject.getInputStream().close();
	}
}