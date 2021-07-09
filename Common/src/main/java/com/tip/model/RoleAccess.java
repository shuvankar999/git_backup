package com.tip.model;

import java.util.List;

public class RoleAccess {

	private List<AccessObject> accessObjectList;
	private ErrorCd errorCd;
	/**
	 * @return the accessObjectList
	 */
	public List<AccessObject> getAccessObjectList() {
		return accessObjectList;
	}
	/**
	 * @param accessObjectList the accessObjectList to set
	 */
	public void setAccessObjectList(List<AccessObject> accessObjectList) {
		this.accessObjectList = accessObjectList;
	}
	/**
	 * @return the errorCd
	 */
	public ErrorCd getErrorCd() {
		return errorCd;
	}
	/**
	 * @param errorCd the errorCd to set
	 */
	public void setErrorCd(ErrorCd errorCd) {
		this.errorCd = errorCd;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleAccess [accessObjectList=" + accessObjectList + ", errorCd=" + errorCd + "]";
	}
	
	
}
