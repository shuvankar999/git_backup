package com.tip.tyrereading.model;

public class TyreReading {

	private String tyreCd;
	private Double tyreDepth;
	private Double tyrePSI;
	private String tyreMake;
	private String tyreSerialNr;
	private String tyreSize;
	/**
	 * @return the tyreCd
	 */
	public String getTyreCd() {
		return tyreCd;
	}
	/**
	 * @param tyreCd the tyreCd to set
	 */
	public void setTyreCd(String tyreCd) {
		this.tyreCd = tyreCd;
	}
	/**
	 * @return the tyreDepth
	 */
	public Double getTyreDepth() {
		return tyreDepth;
	}
	/**
	 * @param tyreDepth the tyreDepth to set
	 */
	public void setTyreDepth(Double tyreDepth) {
		this.tyreDepth = tyreDepth;
	}
	/**
	 * @return the tyrePSI
	 */
	public Double getTyrePSI() {
		return tyrePSI;
	}
	/**
	 * @param tyrePSI the tyrePSI to set
	 */
	public void setTyrePSI(Double tyrePSI) {
		this.tyrePSI = tyrePSI;
	}
	/**
	 * @return the tyreMake
	 */
	public String getTyreMake() {
		return tyreMake;
	}
	/**
	 * @param tyreMake the tyreMake to set
	 */
	public void setTyreMake(String tyreMake) {
		this.tyreMake = tyreMake;
	}
	/**
	 * @return the tyreSerialNr
	 */
	public String getTyreSerialNr() {
		return tyreSerialNr;
	}
	/**
	 * @param tyreSerialNr the tyreSerialNr to set
	 */
	public void setTyreSerialNr(String tyreSerialNr) {
		this.tyreSerialNr = tyreSerialNr;
	}
	/**
	 * @return the tyreSize
	 */
	public String getTyreSize() {
		return tyreSize;
	}
	/**
	 * @param tyreSize the tyreSize to set
	 */
	public void setTyreSize(String tyreSize) {
		this.tyreSize = tyreSize;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TyreReading [tyreCd=" + tyreCd + ", tyreDepth=" + tyreDepth + ", tyrePSI=" + tyrePSI + ", tyreMake="
				+ tyreMake + ", TyreSerialNr=" + tyreSerialNr + ", tyreSize=" + tyreSize + "]";
	}
	
	
	
}
