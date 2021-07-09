package com.tip.fetchattention.service.impl;

import com.tip.fetchattention.model.AttentionRequest;
import com.tip.fetchattention.model.AttentionResponse;
import com.tip.fetchattention.repository.FetchAttentionRepository;
import com.tip.fetchattention.service.FetchAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

;


@Service
@Transactional
public class FetchAttentionServiceImpl implements FetchAttentionService {

    @Autowired
    FetchAttentionRepository fetchAttentionRepository;

    @Override
    public AttentionResponse getAttentionDetails(AttentionRequest attentionRequest) {

        return fetchAttentionRepository.fetchAttentionData(attentionRequest);
    }

}
