package com.tip.fetchattention.controller;

import com.tip.fetchattention.service.FetchAttentionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FetchAttentionControllerTest {

    FetchAttentionController fetchAttentionController;

    @Mock
    FetchAttentionService fetchAttentionService;

    @Before
    public void beforesetup() {
        fetchAttentionController = new FetchAttentionController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchAttentionController, "fetchAttentionService", fetchAttentionService);
    }

    @Test
    public void getAttentionDetails() {
    	try{
            fetchAttentionController.getAttentionDetails(1, 1);
    	}catch(Exception e){}
    }
}
