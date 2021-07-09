package com.tip.estimation.service;

import com.tip.estimation.model.PipelineRequest;

@FunctionalInterface
public interface AddPipelineService {

	public Object addToPipeline(PipelineRequest pipelineRequest);

}
