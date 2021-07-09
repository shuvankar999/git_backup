package com.tip.fetchworkorder.controller;

import com.tip.fetchworkorder.service.FetchWorkorderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

public class FetchWorkorderControllerTest {

    FetchWorkorderController fetchWorkorderController;

    @Mock
    FetchWorkorderService fetchWorkorderService;

    @Before
    public void beforesetup() {
        fetchWorkorderController = new FetchWorkorderController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fetchWorkorderController, "fetchWorkorderService", fetchWorkorderService);
    }

    @Test
    public void getWPackWorderTaskList() {
    	try{

            //long workpack_nr = 2l;
            BigDecimal workpack_nr = BigDecimal.TEN;
            int language_id = 1;
            fetchWorkorderController.getWPackWorderTaskList(workpack_nr, language_id);
    	}catch(Exception e){}
    }
}
