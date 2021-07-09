package com.tip.supplier.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.repository.AccountPayableRepository;

public class AccountPayableServiceImplTest {

	@Mock
	private AccountPayableRepository accountPayableRepository;

	private AccountPayableServiceImpl accountPayableServiceImpl;

	@Before
	public void setUp() throws Exception {
		accountPayableServiceImpl = new AccountPayableServiceImpl();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(accountPayableServiceImpl, "accountPayableRepository", accountPayableRepository);
	}

	@Test
	public void testAccountPayables() {
		try {
			AccountPayableRequest accountPayableRequest = new AccountPayableRequest();

			accountPayableRequest.setSupplierId(5);
			accountPayableRequest.setLangId(5);
			accountPayableRequest.setSsoId("5646521");
			accountPayableRequest.setErrorCd("abc");

			Mockito.when(accountPayableServiceImpl.fetchAccountPayable(Mockito.isA(AccountPayableRequest.class)));

			accountPayableRepository.fetchAccountPayable(accountPayableRequest);
		} catch (Exception e) {
		}
	}
}
