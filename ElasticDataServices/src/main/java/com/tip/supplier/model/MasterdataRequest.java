package com.tip.supplier.model;

import java.util.List;

public class MasterdataRequest {

	protected Integer companyNr;
	protected List<String> listOfSearchText;
	protected String vendorStatus;
	
	public Integer getCompanyNr() {
		return companyNr;
	}
	public List<String> getListOfSearchText() {
		return listOfSearchText;
	}
	public String getVendorStatus() {
		return vendorStatus;
	}
}
