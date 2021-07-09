package com.tip.estimation.model;

import java.math.BigDecimal;

public class ManagementFees {

	private BigDecimal totEstAmtFrom;	
	private BigDecimal totEstAmtTo;
	private Integer feePer;
	private BigDecimal minFee;
	private BigDecimal maxfee;
	private BigDecimal fixedFee;
	
	public BigDecimal getTotEstAmtFrom() {
		return totEstAmtFrom;
	}
	public void setTotEstAmtFrom(BigDecimal totEstAmtFrom) {
		this.totEstAmtFrom = totEstAmtFrom;
	}
	public BigDecimal getTotEstAmtTo() {
		return totEstAmtTo;
	}
	public void setTotEstAmtTo(BigDecimal totEstAmtTo) {
		this.totEstAmtTo = totEstAmtTo;
	}
	public Integer getFeePer() {
		return feePer;
	}
	public void setFeePer(Integer feePer) {
		this.feePer = feePer;
	}
	public BigDecimal getMinFee() {
		return minFee;
	}
	public void setMinFee(BigDecimal minFee) {
		this.minFee = minFee;
	}
	public BigDecimal getMaxfee() {
		return maxfee;
	}
	public void setMaxfee(BigDecimal maxfee) {
		this.maxfee = maxfee;
	}
	public BigDecimal getFixedFee() {
		return fixedFee;
	}
	public void setFixedFee(BigDecimal fixedFee) {
		this.fixedFee = fixedFee;
	}

}
