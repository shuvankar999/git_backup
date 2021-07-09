package com.tip.resourcepipeline.repository;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;

import java.util.Map;

@FunctionalInterface
public interface ResourcePipelineRepository {

    public Map<String, Object> fetchResourcePipelineData(ResourcePipelineRequest resourcePipelineRequest);
}
