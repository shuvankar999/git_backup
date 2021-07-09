package com.tip.supplier.service;

import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.model.OperationalDetailsResponse;

@FunctionalInterface
public interface OperationalDetailsService {

	public OperationalDetailsResponse fetchOperationalDetails(OperationalDetailsRequest operationalDetailsRequest);

}
