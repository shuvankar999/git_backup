package com.tip.estimation.service;

import com.tip.estimation.model.SuplymntryRequest;
import com.tip.estimation.model.SuplymntryResponse;

@FunctionalInterface
public interface SuplymntryDetailsService {

	public SuplymntryResponse getSupplymntryDetails(SuplymntryRequest suplymntryRequest);

}
