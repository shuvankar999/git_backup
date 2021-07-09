package com.tip.estimation.service;

import com.tip.estimation.model.FetchEnrichDetailsRequest;
import com.tip.estimation.model.FetchEnrichDetailsResponse;

@FunctionalInterface
public interface FetchEstnEnrichDetailsService {

	public FetchEnrichDetailsResponse fetchEstEnrichDetails(FetchEnrichDetailsRequest fetchEnrichDetailsRequest);

}
