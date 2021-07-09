package com.tip.resourceplanner.model;

public class AssignedWPDetails {

    private Double workPackNr;
    private String technicianId;
    private String groups;
    private int assetNumber;
    private String customerReferenceNr;
    private String registrationNr;
    private String requiredDoneDate;
    private Double totalGuideTime;
    private int customerNr;
    private String customerName;
    private String chassisNumber;
    private int workOrderCnt;
    private String description;
    private int supplierId;
    private String driverWaiting;
    private int workPackStatusId;
    private String workPackStatus;

    /**
     * @return the workPackNr
     */
    public Double getWorkPackNr() {
        return workPackNr;
    }

    /**
     * @param workPackNr the workPackNr to set
     */
    public void setWorkPackNr(Double workPackNr) {
        this.workPackNr = workPackNr;
    }

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
     * @return the groups
     */
    public String getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(String groups) {
        this.groups = groups;
    }

    /**
     * @return the assetNumber
     */
    public int getAssetNumber() {
        return assetNumber;
    }

    /**
     * @param assetNumber the assetNumber to set
     */
    public void setAssetNumber(int assetNumber) {
        this.assetNumber = assetNumber;
    }

    /**
     * @return the customerReferenceNr
     */
    public String getCustomerReferenceNr() {
        return customerReferenceNr;
    }

    /**
     * @param customerReferenceNr the customerReferenceNr to set
     */
    public void setCustomerReferenceNr(String customerReferenceNr) {
        this.customerReferenceNr = customerReferenceNr;
    }

    /**
     * @return the registrationNr
     */
    public String getRegistrationNr() {
        return registrationNr;
    }

    /**
     * @param registrationNr the registrationNr to set
     */
    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    /**
     * @return the requiredDoneDate
     */
    public String getRequiredDoneDate() {
        return requiredDoneDate;
    }

    /**
     * @param requiredDoneDate the requiredDoneDate to set
     */
    public void setRequiredDoneDate(String requiredDoneDate) {
        this.requiredDoneDate = requiredDoneDate;
    }

    /**
     * @return the totalGuideTime
     */
    public Double getTotalGuideTime() {
        return totalGuideTime;
    }

    /**
     * @param totalGuideTime the totalGuideTime to set
     */
    public void setTotalGuideTime(Double totalGuideTime) {
        this.totalGuideTime = totalGuideTime;
    }

    /**
     * @return the customerNr
     */
    public int getCustomerNr() {
        return customerNr;
    }

    /**
     * @param customerNr the customerNr to set
     */
    public void setCustomerNr(int customerNr) {
        this.customerNr = customerNr;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the chassisNumber
     */
    public String getChassisNumber() {
        return chassisNumber;
    }

    /**
     * @param chassisNumber the chassisNumber to set
     */
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    /**
     * @return the workOrderCnt
     */
    public int getWorkOrderCnt() {
        return workOrderCnt;
    }

    /**
     * @param workOrderCnt the workOrderCnt to set
     */
    public void setWorkOrderCnt(int workOrderCnt) {
        this.workOrderCnt = workOrderCnt;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the driverWaiting
     */
    public String getDriverWaiting() {
        return driverWaiting;
    }

    /**
     * @param driverWaiting the driverWaiting to set
     */
    public void setDriverWaiting(String driverWaiting) {
        this.driverWaiting = driverWaiting;
    }

    /**
     * @return the workPackStatusId
     */
    public int getWorkPackStatusId() {
        return workPackStatusId;
    }

    /**
     * @param workPackStatusId the workPackStatusId to set
     */
    public void setWorkPackStatusId(int workPackStatusId) {
        this.workPackStatusId = workPackStatusId;
    }

    /**
     * @return the workPackStatus
     */
    public String getWorkPackStatus() {
        return workPackStatus;
    }

    /**
     * @param workPackStatus the workPackStatus to set
     */
    public void setWorkPackStatus(String workPackStatus) {
        this.workPackStatus = workPackStatus;
    }
    
    @Override
    public String toString() {
        return "AssignedWPDetails [workPackNr=" + workPackNr + ", technicianId=" + technicianId + ", groups=" + groups
                + ", assetNumber=" + assetNumber + ", customerReferenceNr=" + customerReferenceNr + ", registrationNr="
                + registrationNr + ", requiredDoneDate=" + requiredDoneDate + ", totalGuideTime=" + totalGuideTime
                + ", customerNr=" + customerNr + ", customerName=" + customerName + ", chassisNumber=" + chassisNumber
                + ", workOrderCnt=" + workOrderCnt + ", description=" + description + ", supplierId=" + supplierId
                + ", driverWaiting=" + driverWaiting + ", workPackStatusId=" + workPackStatusId + ", workPackStatus="
                + workPackStatus + "]";
    }


}
