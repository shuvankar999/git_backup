package com.tip.supplier.service;

import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.model.AccountPayableResponse;
@FunctionalInterface
public interface AccountPayableService {

	public AccountPayableResponse fetchAccountPayable(AccountPayableRequest accountPayableRequest);

}
