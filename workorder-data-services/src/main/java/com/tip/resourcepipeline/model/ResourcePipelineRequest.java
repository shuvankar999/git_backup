package com.tip.resourcepipeline.model;

public class ResourcePipelineRequest {

    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResourcePipelineRequest [branchId=" + branchId + "]";
    }


}
