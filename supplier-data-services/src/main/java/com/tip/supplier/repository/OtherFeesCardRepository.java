package com.tip.supplier.repository;

import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.model.OtherFeesCardResponse;

@FunctionalInterface
public interface OtherFeesCardRepository {

	public OtherFeesCardResponse fetchOtherFeesCard(OtherFeesCardRequest otherFeesCardRequest);

}
