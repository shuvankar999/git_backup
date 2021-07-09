package com.tip.supplier.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.repository.OperationalDetailsRepository;

public class OperationalDetailsServiceImplTest {

	@Mock
	private OperationalDetailsRepository operationalDetailsRepository;

	private OperationalDetailsServiceImpl operationalDetailsServiceImpl;

	@Before
	public void setUp() throws Exception {
		operationalDetailsServiceImpl = new OperationalDetailsServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(operationalDetailsServiceImpl, "operationalDetailsRepository",
				operationalDetailsRepository);
	}

	@Test
	public void testOperational() {
		try {
			OperationalDetailsRequest operationalDetailsRequest = new OperationalDetailsRequest();

			operationalDetailsRequest.setSupplierId(5);
			operationalDetailsRequest.setLangId(5);
			operationalDetailsRequest.setSsoId("5646521");
			operationalDetailsRequest.setErrorCd("abc");

			Mockito.when(operationalDetailsServiceImpl
					.fetchOperationalDetails(Mockito.isA(OperationalDetailsRequest.class)));

			operationalDetailsRepository.fetchOperationalDetails(operationalDetailsRequest);
		} catch (Exception e) {
		}
	}
}
