package com.tip.fetchwphistory.service.impl;

import com.tip.fetchwphistory.model.WorkPackRequest;
import com.tip.fetchwphistory.repository.FetchWpHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FetchWpHistoryServiceImplTest {

    FetchWpHistoryServiceImpl fetchWpHistoryServiceImpl;

    @Mock
    FetchWpHistoryRepository fetchWpHistoryRepository;

    @Before
    public void beforesetup() {
        fetchWpHistoryServiceImpl = new FetchWpHistoryServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWpHistoryServiceImpl, "fetchWpHistoryRepository", fetchWpHistoryRepository);
    }

    @Test
    public void getWpHistory() {
        WorkPackRequest workPackRequest = new WorkPackRequest();
        fetchWpHistoryServiceImpl.getWpHistory(workPackRequest);
    }
}
