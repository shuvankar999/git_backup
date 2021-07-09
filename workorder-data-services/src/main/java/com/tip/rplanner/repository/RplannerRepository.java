package com.tip.rplanner.repository;

import java.util.Map;

import com.tip.rplanner.model.ResourcePlannerRequest;

@FunctionalInterface
public interface RplannerRepository {

	public Map<String, Object> getResourcePlannerDetails(ResourcePlannerRequest resourcePlannerRequest);

}
