package com.tip.estimation.repository;

import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichBundleRepository {
	public String saveBundleDetails(SaveEnrichDetails saveEnrichDetails,VersionObject versionObject);

}
