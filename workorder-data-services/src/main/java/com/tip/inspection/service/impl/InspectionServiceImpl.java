package com.tip.inspection.service.impl;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.model.InspectionResponse;
import com.tip.inspection.repository.InspectionRepository;
import com.tip.inspection.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InspectionServiceImpl implements InspectionService {

    @Autowired
    InspectionRepository inspectionRepository;
    
    @Override
    public InspectionResponse getInspectionDetails(InspectionRequest inspectionRequest) {

        return inspectionRepository.fetchInspectionData(inspectionRequest);
    }

}
