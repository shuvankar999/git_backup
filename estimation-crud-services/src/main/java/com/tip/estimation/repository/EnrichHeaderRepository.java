package com.tip.estimation.repository;

import com.tip.estimation.model.EnrichHeaderObject;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichHeaderRepository {
	
	public VersionObject saveEnrichHeader(EnrichHeaderObject EnrichHeaderObject);


}
