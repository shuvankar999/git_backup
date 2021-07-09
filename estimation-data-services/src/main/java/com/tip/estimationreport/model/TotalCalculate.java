package com.tip.estimationreport.model;

import java.math.BigDecimal;

public class TotalCalculate {

	protected BigDecimal subTotal;
	protected BigDecimal consession;
	protected BigDecimal vatRate;
	
	public BigDecimal getSubTotal() {
		if(subTotal==null)
			return BigDecimal.ZERO;
		else
			return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getConsession() {
		return consession;
	}
	public void setConsession(BigDecimal consession) {
		this.consession = consession;
	}
	public BigDecimal getVatRate() {
		return vatRate;
	}
	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
	
	
}
