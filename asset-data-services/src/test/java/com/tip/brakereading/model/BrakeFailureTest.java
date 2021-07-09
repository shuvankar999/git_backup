package com.tip.brakereading.model;



import org.junit.Before;
import org.junit.Test;


public class BrakeFailureTest {

	private BrakeFailure brakeFailure;

	@Before
	public void setUp() throws Exception { brakeFailure = new BrakeFailure();
	brakeFailure.setAssetNr(1);
	brakeFailure.setCountOfFailures(0);
	brakeFailure.setStatus("abc");
	brakeFailure.setUserId("abc");
	brakeFailure.setWorkPackNr(1.00);
	
}


	@Test
	public void test() {
	
	}


}
