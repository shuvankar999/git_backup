package com.tip.supplier.repository;

import com.tip.supplier.model.FeeCardDetailsRequest;
import com.tip.supplier.model.FeeCardDetailsResponse;

@FunctionalInterface
public interface FeeCardDetailsRepository {

	public FeeCardDetailsResponse fetchFeeCardDetails(FeeCardDetailsRequest feeCardDetailsRequest);

}
