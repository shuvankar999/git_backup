package com.tip.supplier.service;

import com.tip.supplier.model.OtherFeesCardRequest;
import com.tip.supplier.model.OtherFeesCardResponse;

@FunctionalInterface
public interface OtherFeesCardService {

	public OtherFeesCardResponse fetchOtherFeesCard(OtherFeesCardRequest otherFeesCardRequest);

}
