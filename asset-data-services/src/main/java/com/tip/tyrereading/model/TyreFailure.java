package com.tip.tyrereading.model;

public class TyreFailure {
	
	private Double workPackNr;
	private int assetNr;
	private String userId;
	private String status;
	private int countOfFailures;
	/**
	 * @return the workPackNrs
	 */
	public Double getWorkPackNr() {
		return workPackNr;
	}
	/**
	 * @param workPackNr the workPackNr to set
	 */
	public void setWorkPackNr(Double workPackNr) {
		this.workPackNr = workPackNr;
	}
	/**
	 * @return the assetNr
	 */
	public int getAssetNr() {
		return assetNr;
	}
	/**
	 * @param assetNr the assetNr to set
	 */
	public void setAssetNr(int assetNr) {
		this.assetNr = assetNr;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the countOfFailures
	 */
	public int getCountOfFailures() {
		return countOfFailures;
	}
	/**
	 * @param countOfFailures the countOfFailures to set
	 */
	public void setCountOfFailures(int countOfFailures) {
		this.countOfFailures = countOfFailures;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TyreFailure [workPackNr=" + workPackNr + ", assetNr=" + assetNr + ", userId=" + userId
				+ ", status=" + status + ", countOfFailures=" + countOfFailures + "]";
	}
	
}
