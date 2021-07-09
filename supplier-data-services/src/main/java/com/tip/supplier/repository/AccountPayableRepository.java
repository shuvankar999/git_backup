package com.tip.supplier.repository;

import com.tip.supplier.model.AccountPayableRequest;
import com.tip.supplier.model.AccountPayableResponse;
@FunctionalInterface
public interface AccountPayableRepository {

	public AccountPayableResponse fetchAccountPayable(AccountPayableRequest accountPayableRequest);

}
