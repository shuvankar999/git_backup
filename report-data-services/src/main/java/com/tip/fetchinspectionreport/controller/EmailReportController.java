package com.tip.fetchinspectionreport.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.fetchinspectionreport.model.EmailReportRequest;
import com.tip.fetchinspectionreport.service.EmailReportService;
import com.tip.validatesession.service.ValidateSessionService;


@RestController
@RequestMapping("/service/report-data-service/2.0/")

public class EmailReportController {

	@Autowired
	EmailReportService emailReportService;

	@Autowired
	ValidateSessionService validateSessionService;
	
	@RequestMapping(value = "emailInspectionReport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendEmail(@RequestBody EmailReportRequest emailReportRequest, HttpServletResponse response) {
		response.getContentType();
		return emailReportService.sendEmail(emailReportRequest);
	}
}