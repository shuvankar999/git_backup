package com.tip.fetchwphistory.model;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;



public class WorkPackRequestTest {
	private WorkPackRequest workPackRequest;
	@Before
	public void setUp() throws Exception { workPackRequest = new WorkPackRequest();
	workPackRequest.setUnitNr(5);
	workPackRequest.setWorkPackNr(BigDecimal.ONE);
	workPackRequest.setWpOpenedDate("");
	
	}
	@Test
	public void test() {
	
	}

}