package com.tip.supplier.service;

import com.tip.supplier.model.MsuDataRequest;
import com.tip.supplier.model.MsuDataResponse;
@FunctionalInterface
public interface MsuDataService {

	public MsuDataResponse fetchMsuData(MsuDataRequest msuDataRequest);

}
