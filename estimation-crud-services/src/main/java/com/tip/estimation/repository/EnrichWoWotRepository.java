package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.EnrichWorkOrderObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface EnrichWoWotRepository {
	
 public String saveEnrichDetails(List<EnrichWorkOrderObject> enrichWorkOrderList,VersionObject versionObject,SaveEnrichDetails saveEnrichDetails);

}
