package com.tip.fetchwohistory.service.impl;

import com.tip.fetchwohistory.model.WorkOrderRequest;
import com.tip.fetchwohistory.repository.FetchWoHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FetchWoHistoryServiceImplTest {

    FetchWoHistoryServiceImpl fetchWoHistoryServiceImpl;

    @Mock
    FetchWoHistoryRepository fetchWoHistoryRepository;

    @Before
    public void beforesetup() {
        fetchWoHistoryServiceImpl = new FetchWoHistoryServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWoHistoryServiceImpl, "fetchWoHistoryRepository", fetchWoHistoryRepository);
    }

    @Test
    public void getWoHistory() {
        WorkOrderRequest workOrderRequest = new WorkOrderRequest();
        fetchWoHistoryServiceImpl.getWoHistory(workOrderRequest);
    }
}
