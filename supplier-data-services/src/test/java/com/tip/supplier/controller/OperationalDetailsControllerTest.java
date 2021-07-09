package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.service.OperationalDetailsService;

public class OperationalDetailsControllerTest {

	@Mock
	private OperationalDetailsService operationalDetailsService;

	private OperationalDetailsController operationalDetailsController;

	@Before
	public void setUp() throws Exception {
		operationalDetailsController = new OperationalDetailsController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(operationalDetailsController, "operationalDetailsService",
				operationalDetailsService);
	}

	@Test
	public void testOperationalDetails() {
		try {
			OperationalDetailsRequest operationalDetailsRequest = new OperationalDetailsRequest();

			operationalDetailsController.fetchOperationalDetails(operationalDetailsRequest);
		} catch (Exception e) {
		}
	}

}