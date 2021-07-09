package com.tip.equipmentdetails.model;

public class MultiCopyResponse {

	protected Integer sourceEquipmentNr;
	protected Integer updateCountsUnits;
	protected Integer updateCountsUnitsDynamic;
	protected String errorCd;
	
	public Integer getSourceEquipmentNr() {
		return sourceEquipmentNr;
	}
	public void setSourceEquipmentNr(Integer sourceEquipmentNr) {
		this.sourceEquipmentNr = sourceEquipmentNr;
	}
	public Integer getUpdateCountsUnits() {
		return updateCountsUnits;
	}
	public void setUpdateCountsUnits(Integer updateCountsUnits) {
		this.updateCountsUnits = updateCountsUnits;
	}
	public Integer getUpdateCountsUnitsDynamic() {
		return updateCountsUnitsDynamic;
	}
	public void setUpdateCountsUnitsDynamic(Integer updateCountsUnitsDynamic) {
		this.updateCountsUnitsDynamic = updateCountsUnitsDynamic;
	}
	public String getErrorCd() {
		return errorCd;
	}
	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}
	
	
}
