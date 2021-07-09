package com.tip.estimation.model;

import java.math.BigDecimal;

public class DiscountMarkup {

	private Integer dscntMrkupGrpId;
	private Integer dscntMrkupSubGrpId;
	private Integer dscntMrkupItmId;
	private String dscntMrkupDesc;
	private BigDecimal minFee;
	private BigDecimal maxfee;
	private BigDecimal fee;
	private BigDecimal fixedFee;
	
	public Integer getDscntMrkupGrpId() {
		return dscntMrkupGrpId;
	}
	public void setDscntMrkupGrpId(Integer dscntMrkupGrpId) {
		this.dscntMrkupGrpId = dscntMrkupGrpId;
	}
	public Integer getDscntMrkupSubGrpId() {
		return dscntMrkupSubGrpId;
	}
	public void setDscntMrkupSubGrpId(Integer dscntMrkupSubGrpId) {
		this.dscntMrkupSubGrpId = dscntMrkupSubGrpId;
	}
	public Integer getDscntMrkupItmId() {
		return dscntMrkupItmId;
	}
	public void setDscntMrkupItmId(Integer dscntMrkupItmId) {
		this.dscntMrkupItmId = dscntMrkupItmId;
	}
	public String getDscntMrkupDesc() {
		return dscntMrkupDesc;
	}
	public void setDscntMrkupDesc(String dscntMrkupDesc) {
		this.dscntMrkupDesc = dscntMrkupDesc;
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
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getFixedFee() {
		return fixedFee;
	}
	public void setFixedFee(BigDecimal fixedFee) {
		this.fixedFee = fixedFee;
	}
	
	
	

}
