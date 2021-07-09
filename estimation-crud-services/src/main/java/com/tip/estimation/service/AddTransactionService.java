package com.tip.estimation.service;

import com.tip.estimation.model.TransactionRequest;

@FunctionalInterface
public interface AddTransactionService {

	public Object addTransaction(TransactionRequest transactionRequest);

}
