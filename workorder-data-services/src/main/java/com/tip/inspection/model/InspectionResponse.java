package com.tip.inspection.model;

import java.util.Map;

public class InspectionResponse {

    private Map<String, Object> inspectionMap;

    /**
     * @return the inspectionMap
     */
    public Map<String, Object> getInspectionMap() {
        return inspectionMap;
    }

    /**
     * @param inspectionMap the inspectionMap to set
     */
    public void setInspectionMap(Map<String, Object> inspectionMap) {
        this.inspectionMap = inspectionMap;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "InspectionResponse [inspectionMap=" + inspectionMap + "]";
    }


}
