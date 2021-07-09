package com.tip.fetchchecklist.model;


import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;



public class InspectionChecklistRequestTest {
	private InspectionChecklistRequest inspectionChecklistRequest;
	@Before
	public void setUp() throws Exception { inspectionChecklistRequest = new InspectionChecklistRequest();
	inspectionChecklistRequest.setLanguageId(4);
	inspectionChecklistRequest.setWorkOrderNr(4);
	inspectionChecklistRequest.setWorkOrderTaskNr(4);
	inspectionChecklistRequest.setWorkPackNr(BigDecimal.ONE);
	}

	@Test
	public void test() {
	
	}

}