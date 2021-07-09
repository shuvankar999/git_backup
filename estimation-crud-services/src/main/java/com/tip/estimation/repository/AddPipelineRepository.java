package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.PipelineRequest;

@FunctionalInterface
public interface AddPipelineRepository {

	Map<String, Object> addToPipeline(PipelineRequest pipelineRequest);

}
