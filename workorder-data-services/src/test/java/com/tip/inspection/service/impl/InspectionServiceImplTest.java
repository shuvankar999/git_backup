package com.tip.inspection.service.impl;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.repository.InspectionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

;

public class InspectionServiceImplTest {

    InspectionServiceImpl inspectionServiceImpl;

    @Mock
    InspectionRepository inspectionRepository;

    @Before
    public void beforesetup() {
        inspectionServiceImpl = new InspectionServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inspectionServiceImpl, "inspectionRepository", inspectionRepository);
    }

    @Test
    public void getInspectionDetails() {
        InspectionRequest inspectionRequest = new InspectionRequest();
        inspectionServiceImpl.getInspectionDetails(inspectionRequest);
    }
}
