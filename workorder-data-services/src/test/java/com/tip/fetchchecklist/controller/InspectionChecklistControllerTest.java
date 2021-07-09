package com.tip.fetchchecklist.controller;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.service.InspectionChecklistService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class InspectionChecklistControllerTest {

    InspectionChecklistController inspectionChecklistController;

    @Mock
    InspectionChecklistService inspectionChecklistService;

    @Before
    public void beforesetup() {
        inspectionChecklistController = new InspectionChecklistController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inspectionChecklistController, "inspectionChecklistService", inspectionChecklistService);
    }

    @Test
    public void getInspectionChecklist() {
    	try{

            InspectionChecklistRequest inspectionChecklistRequest = new InspectionChecklistRequest();
            inspectionChecklistController.getInspectionChecklist(inspectionChecklistRequest);
    	}catch(Exception e){}
    }

    @Test
    public void getMaintenanceInspectionList() {
    	try{

            MaintenanceInspectionRequest maintenanceInspectionRequest = new MaintenanceInspectionRequest();
            inspectionChecklistController.getMaintenanceInspectionList(maintenanceInspectionRequest);
    	}catch(Exception e){}
    }
}
