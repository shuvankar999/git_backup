package com.tip.fetchinspectionreport.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.fetchinspectionreport.model.InspectionReportRequest;
import com.tip.fetchinspectionreport.model.ResponseObject;
import com.tip.fetchinspectionreport.service.InspectionReportService;
import com.tip.validatesession.model.SessionDetails;
import com.tip.validatesession.service.ValidateSessionService;

public class InspectionReportControllerTest {

	@Mock
	InspectionReportService inspectionReportService;
	
	InspectionReportController inspectionReportController;

	@Before
	public void beforesetup(){
		inspectionReportController = new InspectionReportController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(inspectionReportController, "inspectionReportService", inspectionReportService);
	}

	@Test
	public void download(){
		try {
			MockHttpServletResponse response = new MockHttpServletResponse();
			InspectionReportRequest inspectionReportRequest = new InspectionReportRequest();
			ResponseObject responseObject = new ResponseObject();
			File f = new File("testFile");
			f.createNewFile();
			responseObject.setFile(f);
			InputStream myInputStream = new FileInputStream(f); 
			responseObject.setInputStream(myInputStream);
			FileOutputStream out = new FileOutputStream(f);
			responseObject.setOutputStream(out);
			Mockito.when(inspectionReportService.getInspectionReport(inspectionReportRequest)).thenReturn(responseObject);
			inspectionReportController.download(inspectionReportRequest, response);
			f.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
