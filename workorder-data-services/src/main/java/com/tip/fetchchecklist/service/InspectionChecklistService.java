package com.tip.fetchchecklist.service;

import com.tip.fetchchecklist.model.InspectionChecklistRequest;
import com.tip.fetchchecklist.model.InspectionChecklistResponse;
import com.tip.fetchchecklist.model.MaintenanceInspectionRequest;
import com.tip.fetchchecklist.model.MaintenanceInspectionResponse;

;

public interface InspectionChecklistService {

    public InspectionChecklistResponse getInspectionChecklistDetails(InspectionChecklistRequest inspectionChecklistRequest);

    public MaintenanceInspectionResponse getMaintenanceInspectionDetails(MaintenanceInspectionRequest maintenanceInspectionRequest);
}
