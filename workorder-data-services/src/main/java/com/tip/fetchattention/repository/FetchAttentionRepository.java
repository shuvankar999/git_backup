package com.tip.fetchattention.repository;

import com.tip.fetchattention.model.AttentionRequest;
import com.tip.fetchattention.model.AttentionResponse;

@FunctionalInterface
public interface FetchAttentionRepository {

    public AttentionResponse fetchAttentionData(AttentionRequest attentionRequest);
}
