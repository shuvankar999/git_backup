package com.tip.brakereading.model;

public class BrakeReading {

	private String brakeCd;
	private Double liningRemPer;
	private Double liningRemMM;
	
	/**
	 * @return the brakeCd
	 */
	public String getBrakeCd() {
		return brakeCd;
	}
	/**
	 * @param brakeCd the brakeCd to set
	 */
	public void setBrakeCd(String brakeCd) {
		this.brakeCd = brakeCd;
	}
	/**
	 * @return the liningRemPer
	 */
	public Double getLiningRemPer() {
		return liningRemPer;
	}
	/**
	 * @param liningRemPer the liningRemPer to set
	 */
	public void setLiningRemPer(Double liningRemPer) {
		this.liningRemPer = liningRemPer;
	}
	/**
	 * @return the liningRemMM
	 */
	public Double getLiningRemMM() {
		return liningRemMM;
	}
	/**
	 * @param liningRemMM the liningRemMM to set
	 */
	public void setLiningRemMM(Double liningRemMM) {
		this.liningRemMM = liningRemMM;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BrakeReading [brakeCd=" + brakeCd + ", liningRemPer=" + liningRemPer + ", liningRemMM=" + liningRemMM
				+ "]";
	}
	
}
