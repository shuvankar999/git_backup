package com.tip.tyrereading.service;

import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.model.TyreReadingResponse;;

@FunctionalInterface
public interface TyreReadingService {

	public TyreReadingResponse getTyreReadingDetails(TyreReadingRequest tyreReadingRequest);
	
}
