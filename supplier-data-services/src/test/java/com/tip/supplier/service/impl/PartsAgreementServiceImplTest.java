package com.tip.supplier.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.PartsAgreementRequest;
import com.tip.supplier.repository.PartsAgreementRepository;

public class PartsAgreementServiceImplTest {

	@Mock
	private PartsAgreementRepository partsAgreementRepository;

	private PartsAgreementServiceImpl partsAgreementServiceImpl;

	@Before
	public void setUp() throws Exception {
		partsAgreementServiceImpl = new PartsAgreementServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(partsAgreementServiceImpl, "partsAgreementRepository", partsAgreementRepository);
	}

	@Test
	public void testOtherfees() {
		try {
			PartsAgreementRequest partsAgreementRequest = new PartsAgreementRequest();

			partsAgreementRequest.setSupplierId(5);
			partsAgreementRequest.setLangId(5);
			partsAgreementRequest.setSsoId("5646521");
			partsAgreementRequest.setErrorCd("abc");

			Mockito.when(partsAgreementServiceImpl.fetchPartsAgreement(Mockito.isA(PartsAgreementRequest.class)));

			partsAgreementRepository.fetchPartsAgreement(partsAgreementRequest);
		} catch (Exception e) {
		}
	}
}
