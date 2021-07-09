package com.tip.fetchchecklist.service.impl;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.repository.InspectionChecklistRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

;

public class InspectionChecklistServiceImplTest {

    InspectionChecklistServiceImpl inspectionChecklistServiceImpl;

    @Mock
    InspectionChecklistRepository inspectionChecklistRepository;

    @Before
    public void beforesetup() {
        inspectionChecklistServiceImpl = new InspectionChecklistServiceImpl();
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(inspectionChecklistServiceImpl, "inspectionChecklistRepository", inspectionChecklistRepository);
    }

    @Test
    public void getInspectionChecklistDetails() {
        InspectionChecklistRequest inspectionChecklistRequest = new InspectionChecklistRequest();
        inspectionChecklistServiceImpl.getInspectionChecklistDetails(inspectionChecklistRequest);
    }

    @Test
    public void getMaintenanceInspectionDetails() {
        MaintenanceInspectionRequest maintenanceInspectionRequest = new MaintenanceInspectionRequest();
        inspectionChecklistServiceImpl.getMaintenanceInspectionDetails(maintenanceInspectionRequest);
    }
}
