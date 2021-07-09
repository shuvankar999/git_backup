package com.tip.supplier.service;

import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.model.FeeCardDetailsResponse;

@FunctionalInterface
public interface FeeCardDetailsService {

	public FeeCardDetailsResponse fetchFeeCardDetails(FeeCardDetailsRequest feeCardDetailsRequest);

}
