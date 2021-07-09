package com.tip.resourceplanner.model;

import java.util.List;
import java.util.Map;

public class ResourcePlannerResponse {

    private List<TechnicianDetails> technicianDetailsList;

    private Map<String, Object> technicianDetailsMap;

    private Map<String, Object> resourcePlannerMap;

    /**
     * @return the technicianDetailsList
     */
    public List<TechnicianDetails> getTechnicianDetailsList() {
        return technicianDetailsList;
    }

    /**
     * @param technicianDetailsList the technicianDetailsList to set
     */
    public void setTechnicianDetailsList(List<TechnicianDetails> technicianDetailsList) {
        this.technicianDetailsList = technicianDetailsList;
    }

    public Map<String, Object> getTechnicianDetailsMap() {
        return technicianDetailsMap;
    }

    public void setTechnicianDetailsMap(Map<String, Object> technicianDetailsMap) {
        this.technicianDetailsMap = technicianDetailsMap;
    }

    public Map<String, Object> getResourcePlannerMap() {
        return resourcePlannerMap;
    }

    public void setResourcePlannerMap(Map<String, Object> resourcePlannerMap) {
        this.resourcePlannerMap = resourcePlannerMap;
    }

    @Override
    public String toString() {
        return "ResourcePlannerResponse [" + "technicianDetailsList" + technicianDetailsList + "technicianDetailsMap" + technicianDetailsMap + ", resourcePlannerMap" + resourcePlannerMap + "]";
    }


}
