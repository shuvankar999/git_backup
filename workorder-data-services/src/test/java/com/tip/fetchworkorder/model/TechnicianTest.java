package com.tip.fetchworkorder.model;


import org.junit.Before;
import org.junit.Test;

public class TechnicianTest {

	private Technician technician;
	@Before
	public void setUp() throws Exception { technician = new Technician();
	technician.setTechnicianId("ssa");
	technician.setTechnicianName("abc");
	
	}
	@Test
	public void test() {
	
	}

}