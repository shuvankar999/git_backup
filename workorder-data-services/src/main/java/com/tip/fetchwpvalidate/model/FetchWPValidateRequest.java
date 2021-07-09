package com.tip.fetchwpvalidate.model;

public class FetchWPValidateRequest {

    private String ssoId;
    private String branchId;

    /**
     * @return the workPackNr
     */
    public String getSsoId() {
        return ssoId;
    }

    /**
     * @param workPackNr the workPackNr to set
     */
    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "FetchWPValidateRequest [ssoId=" + ssoId + ", branchId=" + branchId + "]";
    }


}
