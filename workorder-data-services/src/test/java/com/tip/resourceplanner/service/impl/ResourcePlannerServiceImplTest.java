package com.tip.resourceplanner.service.impl;

import com.tip.resourceplanner.model.ResourcePlannerRequest;
import com.tip.resourceplanner.repository.ResourcePlannerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

;

public class ResourcePlannerServiceImplTest {

    ResourcePlannerServiceImpl resourcePlannerServiceImpl;

    @Mock
    ResourcePlannerRepository resourcePlannerRepository;

    @Before
    public void beforesetup() {
        resourcePlannerServiceImpl = new ResourcePlannerServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(resourcePlannerServiceImpl, "resourcePlannerRepository", resourcePlannerRepository);
    }

    @Test
    public void getResourcePlannerDetails() {
        ResourcePlannerRequest resourcePlannerRequest = new ResourcePlannerRequest();
        resourcePlannerServiceImpl.getResourcePlannerDetails(resourcePlannerRequest);
    }
}
