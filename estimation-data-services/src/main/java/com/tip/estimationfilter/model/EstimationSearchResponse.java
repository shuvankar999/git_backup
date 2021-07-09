package com.tip.estimationfilter.model;

public class EstimationSearchResponse {

	protected String estimationNr;
	protected String estimationId;
	protected String estimationDate;
	//protected String totalPrice;
	protected String assetNr;
	protected String customerNr;
	protected String customerName;
	protected String estimationTitle;
	protected String estimationBy;
	protected String estimationStatus;
	protected String location;
	protected String country;
	protected String branch;
	protected String custAssetNr;
	protected Object totalPrice;
	protected Object contributionValue;
	
	public String getEstimationNr() {
		return estimationNr;
	}
	public void setEstimationNr(String estimationNr) {
		this.estimationNr = estimationNr;
	}
	public String getEstimationId() {
		return estimationId;
	}
	public void setEstimationId(String estimationId) {
		this.estimationId = estimationId;
	}
	public String getEstimationDate() {
		return estimationDate;
	}
	public void setEstimationDate(String estimationDate) {
		this.estimationDate = estimationDate;
	}
/*	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}*/
	public String getAssetNr() {
		return assetNr;
	}
	public void setAssetNr(String assetNr) {
		this.assetNr = assetNr;
	}
	public String getCustomerNr() {
		return customerNr;
	}
	public void setCustomerNr(String customerNr) {
		this.customerNr = customerNr;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEstimationTitle() {
		return estimationTitle;
	}
	public void setEstimationTitle(String estimationTitle) {
		this.estimationTitle = estimationTitle;
	}
	public String getEstimationBy() {
		return estimationBy;
	}
	public void setEstimationBy(String estimationBy) {
		this.estimationBy = estimationBy;
	}
	public String getEstimationStatus() {
		return estimationStatus;
	}
	public void setEstimationStatus(String estimationStatus) {
		this.estimationStatus = estimationStatus;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCustAssetNr() {
		return custAssetNr;
	}
	public void setCustAssetNr(String custAssetNr) {
		this.custAssetNr = custAssetNr;
	}
	public Object getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Object totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Object getContributionValue() {
		return contributionValue;
	}
	public void setContributionValue(Object contributionValue) {
		this.contributionValue = contributionValue;
	}
	
}
