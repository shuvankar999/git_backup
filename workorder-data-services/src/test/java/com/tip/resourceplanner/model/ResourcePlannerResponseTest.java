package com.tip.resourceplanner.model;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ResourcePlannerResponseTest {

	private ResourcePlannerResponse resourcePlannerResponse;

	@Before
	public void setUp() throws Exception {
		resourcePlannerResponse = new ResourcePlannerResponse();
		Map<String, Object> resourcePlannerMap = null;
		resourcePlannerResponse.setResourcePlannerMap(resourcePlannerMap);
		List<TechnicianDetails> technicianDetailsList = null;
		resourcePlannerResponse.setTechnicianDetailsList(technicianDetailsList);
		Map<String, Object> technicianDetailsMap = null;
		resourcePlannerResponse.setTechnicianDetailsMap(technicianDetailsMap);
	}

	@Test
	public void test() {

	}

}