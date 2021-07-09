package com.tip.resourcepipeline.service;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;
import com.tip.resourcepipeline.model.ResourcePipelineResponse;

@FunctionalInterface
public interface ResourcePipelineService {

    public ResourcePipelineResponse getResourcePipelineDetails(ResourcePipelineRequest resourcePipelineRequest);

}
