package com.tip.tyrereading.model;

public class TyreCheck {

	private String code;
	
	private String description;
	
	private String statusFlag;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return statusFlag;
	}
	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TyreCheck [code=" + code + ", description=" + description + ", statusFlag=" + statusFlag + "]";
	}
	
}
