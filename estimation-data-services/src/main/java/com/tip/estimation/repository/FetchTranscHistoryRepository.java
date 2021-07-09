package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.InvoiceRequest;

@FunctionalInterface
public interface FetchTranscHistoryRepository {

	public Map<String, Object> getTransaction(InvoiceRequest invoiceRequest);

}
