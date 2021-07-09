package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.TransactionRequest;

@FunctionalInterface
public interface AddTransactionRepository {

	public Map<String, Object> addTransaction(TransactionRequest transactionRequest);

}
