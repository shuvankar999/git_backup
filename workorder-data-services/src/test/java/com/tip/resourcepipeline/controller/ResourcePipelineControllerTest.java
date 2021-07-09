package com.tip.resourcepipeline.controller;

import com.tip.resourcepipeline.model.ResourcePipelineRequest;
import com.tip.resourcepipeline.service.ResourcePipelineService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class ResourcePipelineControllerTest {

    ResourcePipelineController resourcePipelineController;

    @Mock
    ResourcePipelineService resourcePipelineService;

    @Before
    public void beforesetup() {
        resourcePipelineController = new ResourcePipelineController();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(resourcePipelineController, "resourcePipelineService", resourcePipelineService);
    }

    @Test
    public void getResourcePipeline() {
    	try{
            ResourcePipelineRequest resourcePipelineRequest = new ResourcePipelineRequest();
            resourcePipelineController.getResourcePipeline(resourcePipelineRequest);
    	}catch(Exception e){}
    }
}
