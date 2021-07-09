package com.tip.rplanner.service;

import com.tip.rplanner.model.ResourcePlannerRequest;
import com.tip.rplanner.model.ResourcePlannerResponse;

@FunctionalInterface
public interface RplannerService {

	public ResourcePlannerResponse getResourcePlannerDetails(ResourcePlannerRequest resourcePlannerRequest);

}
