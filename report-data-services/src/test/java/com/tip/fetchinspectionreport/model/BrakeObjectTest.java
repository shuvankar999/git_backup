package com.tip.fetchinspectionreport.model;

import org.junit.Before;
import org.junit.Test;

public class BrakeObjectTest {

	private BrakeObject brakeObject;

	@Before
	public void setUp() throws Exception {
		brakeObject = new BrakeObject();
		brakeObject.setMmLO1("abc");
		brakeObject.setMmLO2("abc");
		brakeObject.setMmLO3("abc");
		brakeObject.setMmLO4("abc");
		brakeObject.setMmLO5("abc");
		brakeObject.setMmRO1("abc");
		brakeObject.setMmRO2("abc");
		brakeObject.setMmRO3("abc");
		brakeObject.setMmRO4("abc");
		brakeObject.setMmRO5("abc");

		brakeObject.getMmLO1();
		brakeObject.getMmLO2();
		brakeObject.getMmLO3();
		brakeObject.getMmLO4();
		brakeObject.getMmLO5();
		brakeObject.getMmRO1();
		brakeObject.getMmRO2();
		brakeObject.getMmRO3();
		brakeObject.getMmRO4();
		brakeObject.getMmRO5();

	}

	@Test
	public void test() {

	}

}
