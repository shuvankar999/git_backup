package com.tip.estimation.repository;

import java.util.List;

import com.tip.estimation.model.EnrichFeeObject;
import com.tip.estimation.model.SaveEnrichDetails;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface SaveEnrichAdminFeeRepository {
	public String saveEnrichDetails(List<EnrichFeeObject> enrichFeeList,VersionObject versionObject,SaveEnrichDetails saveEnrichDetails);
}
