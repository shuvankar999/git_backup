package com.tip.estimation.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


public class EstnPartLists {

    protected BigDecimal estimationId;
    protected String partNumber;
    protected String partDesc;
    protected Integer supplierId;
    protected String currency;
    protected Integer quantity;
    protected BigDecimal costToTip;
    protected BigDecimal costPlus;
    protected BigDecimal retailPrice;
    protected BigDecimal dicount;
    protected Boolean showHide;
    protected Integer estnWOId;
    protected Integer estnWOTId;
    protected String isApproved;
    protected String isRejected;
    protected BigDecimal fixedPrice;
    public BigDecimal getEstimationId() {
        return estimationId;
    }
    public void setEstimationId(BigDecimal value) {
        this.estimationId = value;
    }
    public String getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(String value) {
        this.partNumber = value;
    }
    public String getPartDesc() {
        return partDesc;
    }
    public void setPartDesc(String value) {
        this.partDesc = value;
    }
    public Integer getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Integer value) {
        this.supplierId = value;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String value) {
        this.currency = value;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer value) {
        this.quantity = value;
    }
    public BigDecimal getCostToTip() {
        return costToTip;
    }
    public void setCostToTip(BigDecimal bigDecimal) {
        this.costToTip = bigDecimal;
    }
    public BigDecimal getCostPlus() {
        return costPlus;
    }
    public void setCostPlus(BigDecimal bigDecimal) {
        this.costPlus = bigDecimal;
    }
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(BigDecimal bigDecimal) {
        this.retailPrice = bigDecimal;
    }
    public BigDecimal getDicount() {
        return dicount;
    }
    public void setDicount(BigDecimal bigDecimal) {
        this.dicount = bigDecimal;
    }
    public Boolean getShowHide() {
        return showHide;
    }
    public void setShowHide(Boolean flag) {
        this.showHide = flag;
    }
    public Integer getEstnWOId() {
        return estnWOId;
    }
    public void setEstnWOId(Integer value) {
        this.estnWOId = value;
    }
    public Integer getEstnWOTId() {
        return estnWOTId;
    }
    public void setEstnWOTId(Integer value) {
        this.estnWOTId = value;
    }
    public String getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(String value) {
        this.isApproved = value;
    }
    public String getIsRejected() {
        return isRejected;
    }
    public void setIsRejected(String value) {
        this.isRejected = value;
    }

	public BigDecimal getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(BigDecimal bigDecimal) {
		this.fixedPrice = bigDecimal;
	}
    
    

}
