package com.tip.fetchchecklist.model;

import java.math.BigDecimal;

public class MaintenanceInspectionRequest {

    private String inspTypeCd;
    private int langId;
    private BigDecimal workPackNr;
    private Integer workOrderNr;
    private Integer workOrderTaskNr;

    public String getInspTypeCd() {
        return inspTypeCd;
    }

    public void setInspTypeCd(String inspTypeCd) {
        this.inspTypeCd = inspTypeCd;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    public BigDecimal getWorkPackNr() {
        return workPackNr;
    }

    public void setWorkPackNr(BigDecimal workPackNr) {
        this.workPackNr = workPackNr;
    }

    public Integer getWorkOrderNr() {
        return workOrderNr;
    }

    public void setWorkOrderNr(Integer workOrderNr) {
        this.workOrderNr = workOrderNr;
    }

    public Integer getWorkOrderTaskNr() {
        return workOrderTaskNr;
    }

    public void setWorkOrderTaskNr(Integer workOrderTaskNr) {
        this.workOrderTaskNr = workOrderTaskNr;
    }

    @Override
    public String toString() {
        return "MaintenanceInspectionRequest [inspTypeCd=" + inspTypeCd + ", langId=" + langId + "]";
    }


}
