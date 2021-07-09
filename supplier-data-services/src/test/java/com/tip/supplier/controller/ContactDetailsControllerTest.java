package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.service.ContactDetailsService;

public class ContactDetailsControllerTest {

	@Mock
	private ContactDetailsService contactDetailsService;

	private ContactDetailsController contactDetailsController;

	@Before
	public void setUp() throws Exception {
		contactDetailsController = new ContactDetailsController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(contactDetailsController, "contactDetailsService", contactDetailsService);
	}

	@Test
	public void testContactDetails() {
		try {
			ContactDetailsRequest contactDetailsRequest = new ContactDetailsRequest();

			contactDetailsController.fetchContactDetails(contactDetailsRequest);
		} catch (Exception e) {
		}
	}

}