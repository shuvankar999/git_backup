package com.tip.supplier.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.service.AccountPayableService;

public class AccountPayableControllerTest {

	@Mock
	private AccountPayableService accountPayableService;

	private AccountPayableController accountPayableController;

	@Before
	public void setUp() throws Exception {
		accountPayableController = new AccountPayableController();
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(accountPayableController, "accountPayableService", accountPayableService);
	}

	@Test
	public void testAccountPayables() {
		try {
			AccountPayableRequest accountPayableRequest = new AccountPayableRequest();

			accountPayableController.fetchAccountPayable(accountPayableRequest);
		} catch (Exception e) {
		}
	}

}
