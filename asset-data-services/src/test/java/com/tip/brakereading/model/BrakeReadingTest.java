package com.tip.brakereading.model;

import org.junit.Before;
import org.junit.Test;

public class BrakeReadingTest {

	private BrakeReading brakeReading;

	@Before
	public void setUp() throws Exception { brakeReading = new BrakeReading();
	brakeReading.setLiningRemPer(1.00);
	brakeReading.setLiningRemMM(10.00);
	brakeReading.setBrakeCd("aa");
	
}


	@Test
	public void test() {
	
	}

}
