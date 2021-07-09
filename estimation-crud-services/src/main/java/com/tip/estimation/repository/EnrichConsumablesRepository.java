package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.EnrichConsumablesObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichConsumablesRepository {
	public String saveEnrichDetails(List<EnrichConsumablesObject> enrichConsumablesObjectList,
			VersionObject versionObject , SaveEnrichDetails saveEnrichDetails );

}
