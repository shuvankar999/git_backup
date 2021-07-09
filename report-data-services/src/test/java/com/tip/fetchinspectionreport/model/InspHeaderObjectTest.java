package com.tip.fetchinspectionreport.model;

import org.junit.Before;
import org.junit.Test;

public class InspHeaderObjectTest {
	private InspHeaderObject inspHeaderObject;

	@Before
	public void setUp() throws Exception {
		inspHeaderObject = new InspHeaderObject();

		inspHeaderObject.setChassis("aaa");
		inspHeaderObject.setJobDate("aaa");
		inspHeaderObject.setJobSheetNr("aaa");
		inspHeaderObject.setMileage("aaa");
		inspHeaderObject.setMotDueDate("aaa");
		inspHeaderObject.setOperator("aaa");
		inspHeaderObject.setRegistrationNr("aaa");
		inspHeaderObject.setRoadWorthy("aaa");
		inspHeaderObject.setServiceType("aaa");
		inspHeaderObject.setTrailerNr("aaa");
		
		inspHeaderObject.getChassis();
		inspHeaderObject.getJobDate();
		inspHeaderObject.getJobSheetNr();
		inspHeaderObject.getMileage();
		inspHeaderObject.getMotDueDate();
		inspHeaderObject.getOperator();
		inspHeaderObject.getRegistrationNr();
		inspHeaderObject.getRoadWorthy();
		inspHeaderObject.getServiceType();
		inspHeaderObject.getTrailerNr();

	}

	@Test
	public void test() {

	}

}
