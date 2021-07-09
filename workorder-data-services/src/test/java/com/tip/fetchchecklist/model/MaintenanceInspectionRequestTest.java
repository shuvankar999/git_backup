package com.tip.fetchchecklist.model;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class MaintenanceInspectionRequestTest {
	private MaintenanceInspectionRequest maintenanceInspectionRequest;
	@Before
	public void setUp() throws Exception { maintenanceInspectionRequest = new MaintenanceInspectionRequest();

	maintenanceInspectionRequest.setInspTypeCd("");
	maintenanceInspectionRequest.setLangId(4);
	maintenanceInspectionRequest.setWorkOrderNr(5);
	maintenanceInspectionRequest.setWorkOrderTaskNr(5);
	maintenanceInspectionRequest.setWorkPackNr(BigDecimal.TEN);
	}
	@Test
	public void test() {
	
	}

}