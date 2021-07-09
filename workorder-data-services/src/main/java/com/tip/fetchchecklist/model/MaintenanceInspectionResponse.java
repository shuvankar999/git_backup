package com.tip.fetchchecklist.model;

import java.util.Map;

public class MaintenanceInspectionResponse {

    private Map<String, Object> maintenanceInspectionMap;

    public Map<String, Object> getMaintenanceInspectionMap() {
        return maintenanceInspectionMap;
    }

    public void setMaintenanceInspectionMap(Map<String, Object> maintenanceInspectionMap) {
        this.maintenanceInspectionMap = maintenanceInspectionMap;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MaintenanceInspectionResponse [maintenanceInspectionMap=" + maintenanceInspectionMap + "]";
    }


}
