package com.tip.fetchtechnician.model;


import org.junit.Before;
import org.junit.Test;



public class TechnicianDetailsTest {

	private TechnicianDetails technicianDetails;
	@Before
	public void setUp() throws Exception { technicianDetails = new TechnicianDetails();
	technicianDetails.setTechnicianId("123");
	technicianDetails.setTechnicianName("abd");
	technicianDetails.setTechnicianStatus("11");
	}
	@Test
	public void test() {
	
	}

}