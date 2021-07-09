package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.service.FeeCardDetailsService;

public class FeeCardDetailsControllerTest {

	@Mock
	private FeeCardDetailsService feeCardDetailsService;

	private FeeCardDetailsController feeCardDetailsController;

	@Before
	public void setUp() throws Exception {
		feeCardDetailsController = new FeeCardDetailsController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(feeCardDetailsController, "feeCardDetailsService", feeCardDetailsService);
	}

	@Test
	public void testFeeCards() {
		try {
			FeeCardDetailsRequest feeCardDetailsRequest = new FeeCardDetailsRequest();

			feeCardDetailsController.fetchFeeCardDetails(feeCardDetailsRequest);
		} catch (Exception e) {
		}
	}

}