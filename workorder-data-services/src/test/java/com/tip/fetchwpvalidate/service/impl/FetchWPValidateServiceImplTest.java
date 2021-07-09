package com.tip.fetchwpvalidate.service.impl;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.repository.FetchWPValidateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

;

public class FetchWPValidateServiceImplTest {

    FetchWPValidateServiceImpl fetchWPValidateServiceImpl;

    @Mock
    FetchWPValidateRepository fetchWPValidateRepository;

    @Before
    public void beforesetup() {
        fetchWPValidateServiceImpl = new FetchWPValidateServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWPValidateServiceImpl, "fetchWPValidateRepository", fetchWPValidateRepository);
    }

    @Test
    public void getWPValidateDetails() {
        FetchWPValidateRequest fetchWPValidateRequest = new FetchWPValidateRequest();
        fetchWPValidateServiceImpl.getWPValidateDetails(fetchWPValidateRequest);
    }
}
