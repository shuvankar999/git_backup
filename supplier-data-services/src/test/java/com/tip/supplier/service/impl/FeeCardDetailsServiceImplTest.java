package com.tip.supplier.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.repository.FeeCardDetailsRepository;

public class FeeCardDetailsServiceImplTest {

	@Mock
	private FeeCardDetailsRepository feeCardDetailsRepository;

	private FeeCardDetailsServiceImpl feeCardDetailsServiceImpl;

	@Before
	public void setUp() throws Exception {
		feeCardDetailsServiceImpl = new FeeCardDetailsServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(feeCardDetailsServiceImpl, "feeCardDetailsRepository", feeCardDetailsRepository);
	}

	@Test
	public void testContactDetails() {
		try {
			FeeCardDetailsRequest feeCardDetailsRequest = new FeeCardDetailsRequest();

			feeCardDetailsRequest.setSupplierId(5);
			feeCardDetailsRequest.setLangId(5);
			feeCardDetailsRequest.setSsoId("5646521");
			feeCardDetailsRequest.setErrorCd("abc");

			Mockito.when(feeCardDetailsServiceImpl.fetchFeeCardDetails(Mockito.isA(FeeCardDetailsRequest.class)));

			feeCardDetailsRepository.fetchFeeCardDetails(feeCardDetailsRequest);
		} catch (Exception e) {
		}
	}
}
