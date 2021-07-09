package com.tip.inspection.controller;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.service.InspectionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class InspectionControllerTest {

    InspectionController inspectionController;

    @Mock
    InspectionService inspectionService;

    @Before
    public void beforesetup() {
        inspectionController = new InspectionController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inspectionController, "inspectionService", inspectionService);
    }

    @Test
    public void getResourcePipeline() {
    	try{
            InspectionRequest inspectionRequest = new InspectionRequest();
            inspectionController.getResourcePipeline(inspectionRequest);
    	}catch(Exception e){}
    }
}
