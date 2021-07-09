package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.EnrichTyreSpecsObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichTyreSpecsRepository {
	public String saveEnrichDetails(List<EnrichTyreSpecsObject> enrichTyreSpecsObjectList,VersionObject versionObject,SaveEnrichDetails saveEnrichDetails);

}
