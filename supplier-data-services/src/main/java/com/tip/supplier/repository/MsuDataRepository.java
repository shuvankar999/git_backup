package com.tip.supplier.repository;

import com.tip.supplier.model.MsuDataRequest;
import com.tip.supplier.model.MsuDataResponse;
@FunctionalInterface
public interface MsuDataRepository {

	public MsuDataResponse fetchMsuData(MsuDataRequest msuDataRequest);

}
