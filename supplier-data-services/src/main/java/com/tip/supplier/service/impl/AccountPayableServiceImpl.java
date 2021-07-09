package com.tip.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.model.AccountPayableResponse;
import com.tip.supplier.repository.AccountPayableRepository;
import com.tip.supplier.service.AccountPayableService;

@Service
@Transactional
public class AccountPayableServiceImpl implements AccountPayableService {

	@Autowired
	AccountPayableRepository accountPayableRepository;
	
	@Override
	public AccountPayableResponse fetchAccountPayable(AccountPayableRequest accountPayableRequest)
	{
		return accountPayableRepository.fetchAccountPayable(accountPayableRequest);
	}
}