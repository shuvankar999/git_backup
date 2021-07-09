package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.EnrichPartsObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichPartsRepository {
	
	public String saveEnrichDetails(List<EnrichPartsObject> enrichPartsObjectList,VersionObject versionObject,SaveEnrichDetails saveEnrichDetails);

}
