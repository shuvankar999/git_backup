package com.tip.technicianjob.controller;

import com.tip.technicianjob.service.TechnicianJobDataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;


public class TechnicianJobDataControllerTest {

    TechnicianJobDataController technicianJobDataController;

    @Mock
    TechnicianJobDataService technicianJobDataService;

    @Before
    public void beforesetup() {
        technicianJobDataController = new TechnicianJobDataController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(technicianJobDataController, "technicianJobDataService", technicianJobDataService);
    }

    @Test
    public void getTechJobDataList() {
    	try{
    		String sso_id = "215";
            String branch_id = "3245";
            technicianJobDataController.getTechJobDataList(sso_id, branch_id);
    	}catch(Exception e){}
    }
}
