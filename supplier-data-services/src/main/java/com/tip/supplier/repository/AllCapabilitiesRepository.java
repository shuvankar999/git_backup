package com.tip.supplier.repository;

import com.tip.supplier.model.AllCapabilitiesRequest;
import com.tip.supplier.model.AllCapabilitiesResponse;

@FunctionalInterface
public interface AllCapabilitiesRepository {

	public AllCapabilitiesResponse fetchAllCapabilities(AllCapabilitiesRequest allCapabilitiesRequest);

}
