package com.tip.resourceplanner.repository;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.model.ResourcePlannerResponse;

@FunctionalInterface
public interface ResourcePlannerRepository {

    public ResourcePlannerResponse fetchResourcePlannerData(ResourcePlannerRequest resourcePlannerRequest);
}
