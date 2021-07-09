package com.tip.resourceplanner.model;

import org.junit.Before;
import org.junit.Test;

public class AssignedWPDetailsTest {

	private AssignedWPDetails assignedWPDetails;

	@Before
	public void setUp() throws Exception {
		assignedWPDetails = new AssignedWPDetails();
		assignedWPDetails.setAssetNumber(5);
		assignedWPDetails.setChassisNumber("aa");
		assignedWPDetails.setCustomerName("aa");
		assignedWPDetails.setCustomerNr(5);
		assignedWPDetails.setCustomerReferenceNr("aa");
		assignedWPDetails.setDescription("aa");
		assignedWPDetails.setDriverWaiting("aa");
		assignedWPDetails.setGroups("aa");
		assignedWPDetails.setRegistrationNr("aa");
		assignedWPDetails.setRequiredDoneDate("aa");
		assignedWPDetails.setSupplierId(5);
		assignedWPDetails.setTechnicianId("aaa");
		assignedWPDetails.setTotalGuideTime(5d);
		assignedWPDetails.setWorkOrderCnt(5);
		assignedWPDetails.setWorkPackStatus("ss");
		assignedWPDetails.setWorkPackStatusId(5);
		assignedWPDetails.setWorkPackNr(5d);

	}

	@Test
	public void test() {

	}

}