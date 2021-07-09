package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.EnrichTyreServiceObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichTyreServiceRepository {
	public String saveEnrichDetails(List<EnrichTyreServiceObject> enrichTyreServiceObjectList,VersionObject versionObject,SaveEnrichDetails saveEnrichDetails);

}
