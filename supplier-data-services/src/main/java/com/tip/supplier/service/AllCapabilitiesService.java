package com.tip.supplier.service;

import com.tip.supplier.model.AllCapabilitiesRequest;
import com.tip.supplier.model.AllCapabilitiesResponse;

@FunctionalInterface
public interface AllCapabilitiesService {

	public AllCapabilitiesResponse fetchAllCapabilities(AllCapabilitiesRequest allCapabilitiesRequest);

}
