package com.tip.supplier.service;

import com.tip.supplier.model.LabourRatesRequest;
import com.tip.supplier.model.LabourRatesResponse;
@FunctionalInterface
public interface LabourRatesService {

	public LabourRatesResponse fetchLabourRates(LabourRatesRequest labourRatesRequest);

}
