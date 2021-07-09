package com.tip.assetreading.model;

import java.math.BigDecimal;

public class AssetReadingRequest {

	private BigDecimal workPackNr;
	private Integer workOrderNr;
	private Integer workOrderTaskNr;
	private Integer languageId;
	
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

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	@Override
	public String toString() {
		return "AssetReadingRequest [workPackNr=" + workPackNr + ", workOrderNr=" + workOrderNr +", workOrderTaskNr=" +workOrderTaskNr+ ", languageId=" +languageId+ "]";
	}
	
	
}
