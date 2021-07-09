package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.service.OtherFeesCardService;

public class OtherFeesCardControllerTest {

	@Mock
	private OtherFeesCardService otherFeesCardService;

	private OtherFeesCardController otherFeesCardController;

	@Before
	public void setUp() throws Exception {
		otherFeesCardController = new OtherFeesCardController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(otherFeesCardController, "otherFeesCardService", otherFeesCardService);
	}

	@Test
	public void testOtherFeecards() {
		try {
			OtherFeesCardRequest otherFeesCardRequest = new OtherFeesCardRequest();

			otherFeesCardController.fetchOtherFeesCard(otherFeesCardRequest);
		} catch (Exception e) {
		}
	}

}