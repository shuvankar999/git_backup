package com.tip.fetchwphistory.controller;

import com.tip.fetchwphistory.model.WorkPackRequest;
import com.tip.fetchwphistory.service.FetchWpHistoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FetchWpHistoryControllerTest {

    FetchWpHistoryController fetchWpHistoryController;

    @Mock
    FetchWpHistoryService fetchWpHistoryService;

    @Before
    public void beforesetup() {
        fetchWpHistoryController = new FetchWpHistoryController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWpHistoryController, "fetchWpHistoryService", fetchWpHistoryService);
    }

    @Test
    public void getWpHistory() {
    	try{

            WorkPackRequest workPackRequest = new WorkPackRequest();
            fetchWpHistoryController.getWpHistory(workPackRequest);
    	}catch(Exception e){}
    }
}
