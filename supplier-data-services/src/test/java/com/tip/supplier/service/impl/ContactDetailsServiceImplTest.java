package com.tip.supplier.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.ContactDetailsRequest;
import com.tip.supplier.repository.ContactDetailsRepository;

public class ContactDetailsServiceImplTest {

	@Mock
	private ContactDetailsRepository contactDetailsRepository;

	private ContactDetailsServiceImpl contactDetailsServiceImpl;

	@Before
	public void setUp() throws Exception {
		contactDetailsServiceImpl = new ContactDetailsServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(contactDetailsServiceImpl, "contactDetailsRepository", contactDetailsRepository);
	}

	@Test
	public void testContactDetails() {
		try {
			ContactDetailsRequest contactDetailsRequest = new ContactDetailsRequest();

			contactDetailsRequest.setSupplierId(5);
			contactDetailsRequest.setLangId(5);
			contactDetailsRequest.setSsoId("5646521");
			contactDetailsRequest.setErrorCd("abc");

			Mockito.when(contactDetailsServiceImpl.fetchContactDetails(Mockito.isA(ContactDetailsRequest.class)));

			contactDetailsRepository.fetchContactDetails(contactDetailsRequest);
		} catch (Exception e) {
		}
	}
}
