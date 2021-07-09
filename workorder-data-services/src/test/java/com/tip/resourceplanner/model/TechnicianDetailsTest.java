package com.tip.resourceplanner.model;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class TechnicianDetailsTest {
	private TechnicianDetails technicianDetails;
	@Before
	public void setUp() throws Exception { technicianDetails = new TechnicianDetails();
	
	List<AssignedWPDetails> assignedWPDetailsList = null;
	technicianDetails.setAssignedWPDetailsList(assignedWPDetailsList);
	technicianDetails.setTechnicianId("aa");
	technicianDetails.setTechnicianName("aa");
	technicianDetails.setTechnicianStatus("aa");
	}
	@Test
	public void test() {
	
	}

}