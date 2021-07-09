package com.tip.estimation.service;

import com.tip.estimation.model.InvoiceRequest;
import com.tip.estimation.model.InvoiceResponse;

@FunctionalInterface
public interface ImmediateInvoiceService {

	public InvoiceResponse getInvoiceData(InvoiceRequest invoiceRequest);

}
