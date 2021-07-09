package com.tip.tyrereading.repository;

import com.tip.tyrereading.model.TyreReadingRequest;
import com.tip.tyrereading.model.TyreReadingResponse;

@FunctionalInterface
public interface TyreReadingRepository {

	public TyreReadingResponse  fetchTyreReadingDetails(TyreReadingRequest tyreReadingRequest);
}
