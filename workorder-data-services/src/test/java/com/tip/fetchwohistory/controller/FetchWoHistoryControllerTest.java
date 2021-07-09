package com.tip.fetchwohistory.controller;

import com.tip.fetchwohistory.model.WorkOrderRequest;
import com.tip.fetchwohistory.service.FetchWoHistoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FetchWoHistoryControllerTest {

    FetchWoHistoryController fetchWoHistoryController;

    @Mock
    FetchWoHistoryService fetchWoHistoryService;

    @Before
    public void beforesetup() {
        fetchWoHistoryController = new FetchWoHistoryController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWoHistoryController, "fetchWoHistoryService", fetchWoHistoryService);
    }

    @Test
    public void getWoHistory() {
    	try{
            WorkOrderRequest workOrderRequest = new WorkOrderRequest();
            fetchWoHistoryController.getWoHistory(workOrderRequest);
            }catch(Exception e){}
    }
}
