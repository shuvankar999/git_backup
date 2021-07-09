package com.tip.resourceplanner.model;

import java.util.List;

public class TechnicianDetails {

    private String technicianId;

    private String technicianName;

    private String technicianStatus;

    private List<AssignedWPDetails> assignedWPDetailsList;

    /**
     * @return the technicianId
     */
    public String getTechnicianId() {
        return technicianId;
    }

    /**
     * @param technicianId the technicianId to set
     */
    public void setTechnicianId(String technicianId) {
        this.technicianId = technicianId;
    }

    /**
     * @return the technicianName
     */
    public String getTechnicianName() {
        return technicianName;
    }

    /**
     * @param technicianName the technicianName to set
     */
    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    /**
     * @return the technicianStatus
     */
    public String getTechnicianStatus() {
        return technicianStatus;
    }

    /**
     * @param technicianStatus the technicianStatus to set
     */
    public void setTechnicianStatus(String technicianStatus) {
        this.technicianStatus = technicianStatus;
    }

    public List<AssignedWPDetails> getAssignedWPDetailsList() {
        return assignedWPDetailsList;
    }

    public void setAssignedWPDetailsList(List<AssignedWPDetails> assignedWPDetailsList) {
        this.assignedWPDetailsList = assignedWPDetailsList;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TechnicianDetails [technicianId=" + technicianId + ", technicianName=" + technicianName
                + ", technicianStatus=" + technicianStatus + "assignedWPDetailsList" + assignedWPDetailsList + "]";
    }


}
