package com.tip.estimation.service;

import com.tip.estimation.model.FetchEstnConsumablesRequest;
import com.tip.estimation.model.FetchEstnConsumablesResponse;

@FunctionalInterface
public interface FetchEstnConsumablesDetailsService {

	public FetchEstnConsumablesResponse getConsumablesDetails(FetchEstnConsumablesRequest fetchEstnConsumablesRequest);

}
