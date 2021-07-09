package com.tip.resourceplanner.controller;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.service.ResourcePlannerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class ResourcePlannerControllerTest {

    ResourcePlannerController resourcePlannerController;

    @Mock
    ResourcePlannerService resourcePlannerService;

    @Before
    public void beforesetup() {
        resourcePlannerController = new ResourcePlannerController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(resourcePlannerController, "resourcePlannerService", resourcePlannerService);
    }

    @Test
    public void getResourcePlanner() {
        ResourcePlannerRequest resourcePlannerRequest = new ResourcePlannerRequest();
        resourcePlannerController.getResourcePlanner(resourcePlannerRequest);
    }
}
