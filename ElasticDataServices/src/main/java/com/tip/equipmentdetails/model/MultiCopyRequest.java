package com.tip.equipmentdetails.model;

import java.util.ArrayList;
import java.util.List;

public class MultiCopyRequest {

	protected Integer sourceEquipmentNr;
	protected List<String> dbColumnNames;
	protected List<Integer> destinationEquipmentNrs;
	protected String ssoId;
	
	public Integer getSourceEquipmentNr() {
		return sourceEquipmentNr;
	}
	public void setSourceEquipmentNr(Integer sourceEquipmentNr) {
		this.sourceEquipmentNr = sourceEquipmentNr;
	}
	public List<String> getDbColumnNames() {
		if (dbColumnNames == null) {
			dbColumnNames = new ArrayList<String>();
        }
		return dbColumnNames;
	}
	public List<Integer> getDestinationEquipmentNrs() {
		if(destinationEquipmentNrs == null) {
			destinationEquipmentNrs = new ArrayList<Integer>();
		}
		return destinationEquipmentNrs;
	}
	public String getSsoId() {
		return ssoId;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	
	
}
