package com.tip.brakereading.model;



import org.junit.Before;
import org.junit.Test;


public class BrakeCheckTest {
	private BrakeCheck brakeCheck;

	@Before
	public void setUp() throws Exception { brakeCheck = new BrakeCheck();
	brakeCheck.setDescription("abcd");
	brakeCheck.setCode("abc");
	brakeCheck.setStatusFlag("aa");
	
	
}
	@Test
	public void test() {
}
}