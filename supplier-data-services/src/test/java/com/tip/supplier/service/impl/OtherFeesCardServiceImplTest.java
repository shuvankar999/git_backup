package com.tip.supplier.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.repository.OtherFeesCardRepository;

public class OtherFeesCardServiceImplTest {

	@Mock
	private OtherFeesCardRepository otherFeesCardRepository;

	private OtherFeesCardServiceImpl otherFeesCardServiceImpl;

	@Before
	public void setUp() throws Exception {
		otherFeesCardServiceImpl = new OtherFeesCardServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(otherFeesCardServiceImpl, "otherFeesCardRepository", otherFeesCardRepository);
	}

	@Test
	public void testOtherfees() {
		try {
			OtherFeesCardRequest otherFeesCardRequest = new OtherFeesCardRequest();

			otherFeesCardRequest.setSupplierId(5);
			otherFeesCardRequest.setLangId(5);
			otherFeesCardRequest.setSsoId("5646521");
			otherFeesCardRequest.setErrorCd("abc");

			Mockito.when(otherFeesCardServiceImpl.fetchOtherFeesCard(Mockito.isA(OtherFeesCardRequest.class)));

			otherFeesCardRepository.fetchOtherFeesCard(otherFeesCardRequest);
		} catch (Exception e) {
		}
	}
}
