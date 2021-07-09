package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.InvoiceRequest;

@FunctionalInterface
public interface ImmediateInvService {

	public Map<String, Object> saveRebillData(InvoiceRequest invoiceRequest);

}
