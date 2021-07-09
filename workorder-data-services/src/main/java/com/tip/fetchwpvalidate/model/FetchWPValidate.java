package com.tip.fetchwpvalidate.model;

import java.util.Date;

public class FetchWPValidate {

    private int assetNumber;
    private String customerReferenceNr;
    private String registrationNr;
    private Double workPackNr;
    private Date requireDoneDate;
    private Double totalGuideTime;
    private int customerNr;
    private String customerName;
    private String chassisNumber;
    private int workOrderCnt;
    private String comments;
    private int supplierId;
    private String driverWaitingFlag;
    private Double actualTime;
    private int status;
    private String flag;

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
     * @return the requireDoneDate
     */
    public Date getRequireDoneDate() {
        return requireDoneDate;
    }

    /**
     * @param requireDoneDate the requireDoneDate to set
     */
    public void setRequireDoneDate(Date requireDoneDate) {
        this.requireDoneDate = requireDoneDate;
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
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
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
     * @return the driverWaitingFlag
     */
    public String getDriverWaitingFlag() {
        return driverWaitingFlag;
    }

    /**
     * @param driverWaitingFlag the driverWaitingFlag to set
     */
    public void setDriverWaitingFlag(String driverWaitingFlag) {
        this.driverWaitingFlag = driverWaitingFlag;
    }

    /**
     * @return the actualTime
     */
    public Double getActualTime() {
        return actualTime;
    }

    /**
     * @param actualTime the actualTime to set
     */
    public void setActualTime(Double actualTime) {
        this.actualTime = actualTime;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FetchWPValidate [assetNumber=" + assetNumber + ", customerReferenceNr=" + customerReferenceNr
                + ", registrationNr=" + registrationNr + ", workPackNr=" + workPackNr + ", requireDoneDate="
                + requireDoneDate + ", totalGuideTime=" + totalGuideTime + ", customerNr=" + customerNr
                + ", customerName=" + customerName + ", chassisNumber=" + chassisNumber + ", workOrderCnt="
                + workOrderCnt + ", comments=" + comments + ", supplierId=" + supplierId + ", driverWaitingFlag="
                + driverWaitingFlag + ", actualTime=" + actualTime + ", status=" + status + ", flag=" + flag + "]";
    }


}
