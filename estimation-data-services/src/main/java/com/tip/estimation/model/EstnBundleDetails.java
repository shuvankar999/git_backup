package com.tip.estimation.model;

import java.math.BigDecimal;

public class EstnBundleDetails {

    protected BigDecimal estimationId;
    protected String bundleName;
    protected String dnaCode;
    protected String groupCdDesc;
    protected String subGroup;
    protected String activity;
    protected String actionCd;
    protected String maintenanceAction;
    protected String manufacturerId;
    protected String manufacturer;
    protected String supplierPartNr;
    protected BigDecimal fee;

    public BigDecimal getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(BigDecimal value) {
        this.estimationId = value;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String value) {
        this.bundleName = value;
    }


    public String getDnaCode() {
        return dnaCode;
    }


    public void setDnaCode(String value) {
        this.dnaCode = value;
    }


    public String getGroupCdDesc() {
        return groupCdDesc;
    }


    public void setGroupCdDesc(String value) {
        this.groupCdDesc = value;
    }


    public String getSubGroup() {
        return subGroup;
    }


    public void setSubGroup(String value) {
        this.subGroup = value;
    }


    public String getActivity() {
        return activity;
    }


    public void setActivity(String value) {
        this.activity = value;
    }

 
    public String getActionCd() {
        return actionCd;
    }

    public void setActionCd(String value) {
        this.actionCd = value;
    }

 
    public String getMaintenanceAction() {
        return maintenanceAction;
    }

 
    public void setMaintenanceAction(String value) {
        this.maintenanceAction = value;
    }

 
    public String getManufacturerId() {
        return manufacturerId;
    }

    
    public void setManufacturerId(String value) {
        this.manufacturerId = value;
    }

   
    public String getManufacturer() {
        return manufacturer;
    }

    
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

   
    public String getSupplierPartNr() {
        return supplierPartNr;
    }

    
    public void setSupplierPartNr(String value) {
        this.supplierPartNr = value;
    }

    
    public BigDecimal getFee() {
        return fee;
    }

    
    public void setFee(BigDecimal value) {
        this.fee = value;
    }

}
