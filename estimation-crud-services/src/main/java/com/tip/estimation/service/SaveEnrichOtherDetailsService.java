package com.tip.estimation.service;

import com.tip.estimation.model.EnrichResponse;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface SaveEnrichOtherDetailsService {

	public EnrichResponse saveDetails(SaveEnrichDetails saveEnrichDetails, VersionObject versnObject);
}
