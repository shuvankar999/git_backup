package com.tip.fetchwohistory.model;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class WorkOrderRequestTest {
	private WorkOrderRequest workOrderRequest;
	@Before
	public void setUp() throws Exception { workOrderRequest = new WorkOrderRequest();
	workOrderRequest.setGroupCd("");
	workOrderRequest.setUnitNr(5);
	workOrderRequest.setWorkPackNr(BigDecimal.ONE);
	workOrderRequest.setWpOpenedDate("");
	
	}
	@Test
	public void test() {
	
	}

}