package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.InvoiceRequest;

@FunctionalInterface
public interface ImmediateInvoiceRepository {

	public Map<String, Object> getInvoiceData(InvoiceRequest invoiceRequest);

}
