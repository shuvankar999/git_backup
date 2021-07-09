package com.tip.fetchwpvalidate.controller;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.service.FetchWPValidateService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FetchWPValidateControllerTest {

    FetchWPValidateController fetchWPValidateController;

    @Mock
    FetchWPValidateService fetchWPValidateService;

    @Before
    public void beforesetup() {
        fetchWPValidateController = new FetchWPValidateController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWPValidateController, "fetchWPValidateService", fetchWPValidateService);
    }

    @Test
    public void getAttentionDetails() {
    	try{
            FetchWPValidateRequest fetchWPValidateRequest = new FetchWPValidateRequest();
            fetchWPValidateController.getAttentionDetails(fetchWPValidateRequest);
    	}catch(Exception e){}
    }
}
