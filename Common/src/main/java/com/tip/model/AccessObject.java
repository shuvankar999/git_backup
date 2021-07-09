package com.tip.model;

public class AccessObject {
	
	private String applResrcId;
	private String applGroupId;
	private String applGroupDesc;
	
	/**
	 * @return the applResrcId
	 */
	public String getApplResrcId() {
		return applResrcId;
	}
	/**
	 * @param applResrcId the applResrcId to set
	 */
	public void setApplResrcId(String applResrcId) {
		this.applResrcId = applResrcId;
	}
	/**
	 * @return the applGroupId
	 */
	public String getApplGroupId() {
		return applGroupId;
	}
	/**
	 * @param applGroupId the applGroupId to set
	 */
	public void setApplGroupId(String applGroupId) {
		this.applGroupId = applGroupId;
	}
	/**
	 * @return the applGroupDesc
	 */
	public String getApplGroupDesc() {
		return applGroupDesc;
	}
	/**
	 * @param applGroupDesc the applGroupDesc to set
	 */
	public void setApplGroupDesc(String applGroupDesc) {
		this.applGroupDesc = applGroupDesc;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessObject [applResrcId=" + applResrcId + ", applGroupId=" + applGroupId + ", applGroupDesc="
				+ applGroupDesc + "]";
	}
	
	
	
}
