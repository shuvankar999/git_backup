package com.tip.fetchinspectionreport.controller;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchinspectionreport.model.EmailReportRequest;
import com.tip.fetchinspectionreport.service.EmailReportService;

public class EmailReportControllerTest {

	EmailReportController emailReportController;
	
	@Mock
	EmailReportService emailReportService;
	
	@Before
	public void beforesetup(){
		emailReportController = new EmailReportController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(emailReportController, "emailReportService", emailReportService);
	}

	@Test
	public void sendEmail() {
		try {

			EmailReportRequest emailReportRequest = new EmailReportRequest();
			emailReportRequest.setWorkPackNr(BigDecimal.valueOf(7900181290L));
			emailReportRequest.setWorkOrderNr(1);
			emailReportRequest.setLangId(1);
			emailReportRequest.setSsoId("501538672");
			emailReportRequest.setTo("Akanksha.Katiyar@tipeurope.com");
			emailReportRequest.setBody("Please find the attached Service document.Inspection ID -");
			emailReportController.sendEmail(emailReportRequest,null);
		} catch (Exception e) {
		}
		
	}
}