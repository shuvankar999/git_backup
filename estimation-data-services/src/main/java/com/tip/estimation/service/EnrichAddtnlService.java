package com.tip.estimation.service;

import com.tip.estimation.model.EnrichAddtionalRequest;
import com.tip.estimation.model.EnrichAddtionalResponse;

@FunctionalInterface
public interface EnrichAddtnlService {

	public EnrichAddtionalResponse fetchAddtnlEnrichDetails(EnrichAddtionalRequest enrichAddtionalRequest);

}
