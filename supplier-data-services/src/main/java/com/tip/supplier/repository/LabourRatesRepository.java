package com.tip.supplier.repository;

import com.tip.supplier.model.LabourRatesRequest;
import com.tip.supplier.model.LabourRatesResponse;
@FunctionalInterface
public interface LabourRatesRepository {

	public LabourRatesResponse fetchLabourRates(LabourRatesRequest labourRatesRequest);

}
