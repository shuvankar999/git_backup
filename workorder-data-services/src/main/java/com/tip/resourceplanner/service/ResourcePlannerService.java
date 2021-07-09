package com.tip.resourceplanner.service;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.model.ResourcePlannerResponse;

@FunctionalInterface
public interface ResourcePlannerService {

    public ResourcePlannerResponse getResourcePlannerDetails(ResourcePlannerRequest resourcePlannerRequest);

}
