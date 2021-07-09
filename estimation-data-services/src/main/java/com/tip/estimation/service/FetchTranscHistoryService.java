package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.InvoiceRequest;

@FunctionalInterface
public interface FetchTranscHistoryService {

	public Map<String, Object> getTransaction(InvoiceRequest invoiceRequest);

}
