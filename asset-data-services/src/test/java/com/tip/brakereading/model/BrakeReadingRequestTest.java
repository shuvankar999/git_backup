package com.tip.brakereading.model;



import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class BrakeReadingRequestTest {
	private BrakeReadingRequest brakeReadingRequest;

	@Before
	public void setUp() throws Exception { brakeReadingRequest = new BrakeReadingRequest();

	//brakeReadingRequest.setWorkPackNr(1.00);

	brakeReadingRequest.setWorkPackNr(BigDecimal.TEN);
	brakeReadingRequest.setWorkOrderNr(20);
	brakeReadingRequest.setWorkOrderTaskNr(10);
	brakeReadingRequest.setLanguageId(5);
	
}


	@Test
	public void test() {
	
	}

}
