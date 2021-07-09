package com.tip.fetchaxle.model;

public class FetchAxleRequest {

	private int unitNr;
	
	public int getUnitNr() {
		return unitNr;
	}
	
	public void setUnitNr(int unitNr) {
		this.unitNr = unitNr;
	}

	@Override
	public String toString() {
		return "FetchAxleRequest [unitNr=" + unitNr + "]";
	}
	
	
}
