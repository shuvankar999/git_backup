package com.tip.fetchtechnician.controller;

import com.tip.fetchtechnician.model.TechnicianDetailsRequest;
import com.tip.fetchtechnician.service.TechnicianDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class TechnicianDeatilsControllerTest {

    TechnicianDeatilsController technicianDeatilsController;

    @Mock
    TechnicianDetailsService technicianDetailsService;

    @Before
    public void beforesetup() {
        technicianDeatilsController = new TechnicianDeatilsController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(technicianDeatilsController, "technicianDetailsService", technicianDetailsService);
    }

    @Test
    public void getTechnicianDetails() {
    	try{

            TechnicianDetailsRequest technicianDetailsRequest = new TechnicianDetailsRequest();
            technicianDeatilsController.getTechnicianDetails(technicianDetailsRequest);
    	}catch(Exception e){}
    }
}
