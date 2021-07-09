package com.tip.estimation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EstnWorkOrderObject {

    protected BigDecimal estimationId;
    protected Integer estnWoId;
    protected Integer supplierId;
    protected String supplierName;
    protected String groupCd;
    protected String groupCdDesc;
    protected Integer estnWotCnt;
    protected Integer estnPartsCnt;
    protected String woIntComm;
    protected String woCustComm;
    protected Double totTagetTime;
    protected List<EstnPartDetails> estnMatchedPartsList;
    protected List<EstnWorkOrderTaskObject> estnWorkOrderTaskList;

    public BigDecimal getEstimationId() {
        return estimationId;
    }

  
    public void setEstimationId(BigDecimal value) {
        this.estimationId = value;
    }

   
    public Integer getEstnWoId() {
        return estnWoId;
    }

    
    public void setEstnWoId(Integer value) {
        this.estnWoId = value;
    }

    
    public Integer getSupplierId() {
        return supplierId;
    }

    
    public void setSupplierId(Integer value) {
        this.supplierId = value;
    }

    
    public String getSupplierName() {
        return supplierName;
    }

    
    public void setSupplierName(String value) {
        this.supplierName = value;
    }

    
    public String getGroupCd() {
        return groupCd;
    }

    
    public void setGroupCd(String value) {
        this.groupCd = value;
    }

    
    public String getGroupCdDesc() {
        return groupCdDesc;
    }

    
    public void setGroupCdDesc(String value) {
        this.groupCdDesc = value;
    }

    
    public Integer getEstnWotCnt() {
        return estnWotCnt;
    }

    
    public void setEstnWotCnt(Integer value) {
        this.estnWotCnt = value;
    }

    
    public Integer getEstnPartsCnt() {
        return estnPartsCnt;
    }

   
    public void setEstnPartsCnt(Integer value) {
        this.estnPartsCnt = value;
    }

    
    public String getWoIntComm() {
        return woIntComm;
    }

    
    public void setWoIntComm(String value) {
        this.woIntComm = value;
    }

    
    public String getWoCustComm() {
        return woCustComm;
    }

    public void setWoCustComm(String value) {
        this.woCustComm = value;
    }

    public Double getTotTagetTime() {
        return totTagetTime;
    }

    
    public void setTotTagetTime(Double double1) {
        this.totTagetTime = double1;
    }

    public List<EstnWorkOrderTaskObject> getEstnWorkOrderTaskList() {
        if (estnWorkOrderTaskList == null) {
            estnWorkOrderTaskList = new ArrayList<EstnWorkOrderTaskObject>();
        }
        return this.estnWorkOrderTaskList;
    }

	public void setEstnWorkOrderTaskList(List<EstnWorkOrderTaskObject> estnWorkOrderTaskList) {
		this.estnWorkOrderTaskList = estnWorkOrderTaskList;
	}
	
	public List<EstnPartDetails> getEstnMatchedPartsList() {
        if (estnMatchedPartsList == null) {
            estnMatchedPartsList = new ArrayList<EstnPartDetails>();
        }
        return this.estnMatchedPartsList;
    }


	public void setEstnMatchedPartsList(List<EstnPartDetails> estnMatchedPartsList) {
		this.estnMatchedPartsList = estnMatchedPartsList;
	}

    	
    

}
