package com.tip.supplier.service;

import com.tip.supplier.model.InsuranceRequest;
import com.tip.supplier.model.InsuranceResponse;
@FunctionalInterface
public interface InsuranceService {

	public InsuranceResponse fetchInsuranceDetails(InsuranceRequest insuranceRequest);

}
