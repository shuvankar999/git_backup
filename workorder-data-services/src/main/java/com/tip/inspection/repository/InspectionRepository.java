package com.tip.inspection.repository;

import com.tip.inspection.model.InspectionRequest;
import com.tip.inspection.model.InspectionResponse;

@FunctionalInterface
public interface InspectionRepository {

    public InspectionResponse fetchInspectionData(InspectionRequest inspectionRequest);
}
