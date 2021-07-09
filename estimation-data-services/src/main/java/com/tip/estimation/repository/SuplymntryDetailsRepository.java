package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.SuplymntryRequest;

@FunctionalInterface
public interface SuplymntryDetailsRepository {

	public Map<String, Object> getSupplymntryDetails(SuplymntryRequest suplymntryRequest);

}
