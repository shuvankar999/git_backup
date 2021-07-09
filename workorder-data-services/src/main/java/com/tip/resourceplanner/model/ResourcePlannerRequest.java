package com.tip.resourceplanner.model;

public class ResourcePlannerRequest {

    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }


    @Override
    public String toString() {
        return "ResourcePlannerRequest [branchId=" + branchId + "]";
    }


}
