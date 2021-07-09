package com.tip.estimation.model;

public class EstnFeeList {
	
	protected int feesgroupId;
	protected int feessubgroupId;
	protected int feesitemId;
    protected String feeDesc;
    
    protected Object fee;
	protected Object charges;
	protected Boolean showHide;
    protected String isApproved;
    
    protected String isRejected;

	public int getFeesgroupId() {
		return feesgroupId;
	}

	public void setFeesgroupId(int feesgroupId) {
		this.feesgroupId = feesgroupId;
	}

	public int getFeessubgroupId() {
		return feessubgroupId;
	}

	public void setFeessubgroupId(int feessubgroupId) {
		this.feessubgroupId = feessubgroupId;
	}

	public int getFeesitemId() {
		return feesitemId;
	}

	public void setFeesitemId(int feesitemId) {
		this.feesitemId = feesitemId;
	}

	public String getFeeDesc() {
		return feeDesc;
	}

	public void setFeeDesc(String feeDesc) {
		this.feeDesc = feeDesc;
	}

	public Object getFee() {
		return fee;
	}

	public void setFee(Object fee) {
		this.fee = fee;
	}

	public Object getCharges() {
		return charges;
	}

	public void setCharges(Object charges) {
		this.charges = charges;
	}

	public Boolean getShowHide() {
		return showHide;
	}

	public void setShowHide(Boolean showHide) {
		this.showHide = showHide;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(String isRejected) {
		this.isRejected = isRejected;
	}
    

}
