package com.tip.inspection.service;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.model.InspectionResponse;

@FunctionalInterface
public interface InspectionService {

    public InspectionResponse getInspectionDetails(InspectionRequest inspectionRequest);

}
