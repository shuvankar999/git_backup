package com.tip.fetchattention.service;

import com.tip.fetchattention.model.AttentionRequest;
import com.tip.fetchattention.model.AttentionResponse;

@FunctionalInterface
public interface FetchAttentionService {

    public AttentionResponse getAttentionDetails(AttentionRequest attentionRequest);

}
