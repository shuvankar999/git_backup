package com.tip.supplier.repository;

import com.tip.supplier.model.InsuranceRequest;
import com.tip.supplier.model.InsuranceResponse;
@FunctionalInterface
public interface InsuranceRepository {

	public InsuranceResponse fetchInsuranceDetails(InsuranceRequest insuranceRequest);

}
