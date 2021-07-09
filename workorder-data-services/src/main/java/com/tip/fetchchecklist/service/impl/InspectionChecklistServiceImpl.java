package com.tip.fetchchecklist.service.impl;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.InspectionChecklistResponse;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionResponse;
import com.tip.fetchchecklist.repository.InspectionChecklistRepository;
import com.tip.fetchchecklist.service.InspectionChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

;


@Service
@Transactional
public class InspectionChecklistServiceImpl implements InspectionChecklistService {

    @Autowired
    InspectionChecklistRepository inspectionChecklistRepository;

    @Override
    public InspectionChecklistResponse getInspectionChecklistDetails(InspectionChecklistRequest inspectionChecklistRequest) {

        return inspectionChecklistRepository.fetchInspectionPipelineData(inspectionChecklistRequest);
    }

    @Override
    public MaintenanceInspectionResponse getMaintenanceInspectionDetails(MaintenanceInspectionRequest maintenanceInspectionRequest) {

        return inspectionChecklistRepository.fetchMaintenanceInspectionData(maintenanceInspectionRequest);
    }
}
