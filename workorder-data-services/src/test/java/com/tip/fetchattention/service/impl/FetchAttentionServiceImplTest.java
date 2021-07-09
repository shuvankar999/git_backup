package com.tip.fetchattention.service.impl;

import com.tip.fetchattention.model.AttentionRequest;
import com.tip.fetchattention.repository.FetchAttentionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

;


public class FetchAttentionServiceImplTest {

    FetchAttentionServiceImpl fetchAttentionServiceImpl;

    @Mock
    FetchAttentionRepository fetchAttentionRepository;

    @Before
    public void beforesetup() {
        fetchAttentionServiceImpl = new FetchAttentionServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchAttentionServiceImpl, "fetchAttentionRepository", fetchAttentionRepository);
    }

    @Test
    public void getAttentionDetails() {
        AttentionRequest attentionRequest = new AttentionRequest();
        fetchAttentionServiceImpl.getAttentionDetails(attentionRequest);
    }
}
