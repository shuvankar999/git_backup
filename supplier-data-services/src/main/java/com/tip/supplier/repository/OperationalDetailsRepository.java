package com.tip.supplier.repository;

import com.tip.supplier.model.OperationalDetailsRequest;
import com.tip.supplier.model.OperationalDetailsResponse;

@FunctionalInterface
public interface OperationalDetailsRepository {

	public OperationalDetailsResponse fetchOperationalDetails(OperationalDetailsRequest operationalDetailsRequest);

}
