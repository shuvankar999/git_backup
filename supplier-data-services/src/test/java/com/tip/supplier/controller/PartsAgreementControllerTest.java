package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.service.PartsAgreementService;

public class PartsAgreementControllerTest {

	@Mock
	private PartsAgreementService partsAgreementService;

	private PartsAgreementController partsAgreementController;

	@Before
	public void setUp() throws Exception {
		partsAgreementController = new PartsAgreementController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(partsAgreementController, "partsAgreementService", partsAgreementService);
	}

	@Test
	public void testPartsAgreement() {
		try {
			PartsAgreementRequest partsAgreementRequest = new PartsAgreementRequest();

			partsAgreementController.fetchPartsAgreement(partsAgreementRequest);
		} catch (Exception e) {
		}
	}

}