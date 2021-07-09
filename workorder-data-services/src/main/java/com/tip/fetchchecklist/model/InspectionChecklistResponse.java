package com.tip.fetchchecklist.model;

import java.util.Map;

public class InspectionChecklistResponse {

    private Map<String, Object> inspectionChecklistMap;

    public Map<String, Object> getInspectionChecklistMap() {
        return inspectionChecklistMap;
    }

    public void setInspectionChecklistMap(Map<String, Object> inspectionChecklistMap) {
        this.inspectionChecklistMap = inspectionChecklistMap;
    }

    @Override
    public String toString() {
        return "InspectionChecklistResponse [inspectionChecklistMap" + inspectionChecklistMap + "]";
    }


}
