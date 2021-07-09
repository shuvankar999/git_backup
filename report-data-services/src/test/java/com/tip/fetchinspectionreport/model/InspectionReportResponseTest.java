package com.tip.fetchinspectionreport.model;

import org.junit.Before;
import org.junit.Test;

public class InspectionReportResponseTest {

	private InspectionReportResponse inspectionReportResponse;
	@Before
	public void setUp() throws Exception { inspectionReportResponse = new InspectionReportResponse();
	
	inspectionReportResponse.getInspBrakelist();
	inspectionReportResponse.getInspChecklist();
	inspectionReportResponse.getInspDefectlist();
	inspectionReportResponse.getInspHeaderlist();
	inspectionReportResponse.getInspTranslist();
	inspectionReportResponse.getInspTyrelist();
	inspectionReportResponse.getSignlist();
	
	
}


	@Test
	public void test() {
	
	}

}
