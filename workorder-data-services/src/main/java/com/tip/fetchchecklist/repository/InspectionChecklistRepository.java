package com.tip.fetchchecklist.repository;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.InspectionChecklistResponse;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionResponse;

public interface InspectionChecklistRepository {

    public InspectionChecklistResponse fetchInspectionPipelineData(InspectionChecklistRequest inspectionChecklistRequest);

    public MaintenanceInspectionResponse fetchMaintenanceInspectionData(MaintenanceInspectionRequest maintenanceInspectionRequest);
}
