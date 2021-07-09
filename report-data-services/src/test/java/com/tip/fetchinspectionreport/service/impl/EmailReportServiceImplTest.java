package com.tip.fetchinspectionreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchinspectionreport.model.EmailReportRequest;
import com.tip.fetchinspectionreport.repository.EmailReportRepository;
import com.tip.report.util.MailUtil;

public class EmailReportServiceImplTest {

	EmailReportServiceImpl emailReportServiceImpl;

	@Mock
	EmailReportRepository emailReportRepository;

	@Mock
	MailUtil mailUtil;

	@Before
	public void beforesetup() {
		emailReportServiceImpl = new EmailReportServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(emailReportServiceImpl, "emailReportRepository", emailReportRepository);
		ReflectionTestUtils.setField(emailReportServiceImpl, "mailUtil", mailUtil);
	}

	@Test
	public void sendEmail() {
		EmailReportRequest emailReportRequest = new EmailReportRequest();
		emailReportRequest.setWorkPackNr(BigDecimal.valueOf(7900181290L));
		emailReportRequest.setWorkOrderNr(1);
		emailReportRequest.setLangId(1);
		emailReportRequest.setSsoId("501538672");
		emailReportRequest.setTo("Akanksha.Katiyar@tipeurope.com");
		emailReportRequest.setBody("Please find the attached Service document.Inspection ID -");
		try {
			emailReportServiceImpl.sendEmail(emailReportRequest);
		} catch (Exception e) {
		}
		Map<String, Object> returnMap = new HashMap<String, Object>(0);
		List<Object> emailDetailslist = new ArrayList<Object>(0);
		Map<String, Object> emailDetailsMap = new HashMap<String, Object>(0);
		emailDetailsMap.put("SMTP", "10.236.232.40");
		emailDetailsMap.put("FromAdd", "noreply@tipeurope.com");
		emailDetailsMap.put("Subject", "Service document-790018129011-499189-B");
		emailDetailsMap.put("PDFLocation", "/opt2/shared/data/MatrixReports/MaintenanceInspection/7900181290_B.pdf");
		emailDetailsMap.put("UserName", "501704258");
		emailDetailsMap.put("Password", "Pa55word");

		emailDetailslist.add(emailDetailsMap);
		returnMap.put("emailDetailsMap", emailDetailslist);

		Mockito.when(emailReportRepository.sendEmail(emailReportRequest)).thenReturn(returnMap);
		Mockito.when(mailUtil.sendEmail()).thenReturn("SUCCESS");
		try {
			emailReportServiceImpl.sendEmail(emailReportRequest);
		} catch (Exception e) {
		}
		emailReportRequest.setTo("Akanksha.Katiyar@tipeurope.com,Akanksha.Katiyar@tipeurope.com");
		try {
			emailReportServiceImpl.sendEmail(emailReportRequest);
		} catch (Exception e) {
		}
	}
}
