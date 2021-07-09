package com.tip.estimationmailreport.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimationmailreport.model.EstnEmailReportRequest;
import com.tip.estimationmailreport.service.EstnEmailReportService;
import com.tip.validatesession.service.ValidateSessionService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class EstnEmailReportController {
		
		@Autowired
		EstnEmailReportService estnEmailReportService;
		
		@Autowired
		ValidateSessionService validateSessionService;
		
		@RequestMapping(value = "emailEstimationReport", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> sendEmail(@RequestBody EstnEmailReportRequest estnEmailReportRequest, HttpServletResponse response) {
			response.getContentType();
			return estnEmailReportService.sendEmail(estnEmailReportRequest);
		}
	
	}
