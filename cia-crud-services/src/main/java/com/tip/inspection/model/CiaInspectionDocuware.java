package com.tip.inspection.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
@Component
public class CiaInspectionDocuware {
	
	private BigDecimal inspId;
	private String inspType;
	private BigDecimal customerNr;
	private int unitNr;
	private String inspDate;
	private String inspLocation;
	private BigDecimal inspLocLongitude;
	private BigDecimal inspLocLatitude;
	private BigDecimal inspLocAltitude;
	private String inspDriverCompany;
	private String inspRemarks;
	private String userEmail;
	private String timeZoneOffset;
	private String docname;
	private String docstatus;
	private int docuwarreDocId;
	private String appCd;
	
	
	public String getInspType() {
		return inspType;
	}
	public void setInspType(String inspType) {
		this.inspType = inspType;
	}
	
	public String getInspDate() {
		return inspDate;
	}
	public void setInspDate(String inspDate) {
		this.inspDate = inspDate;
	}
	public String getInspLocation() {
		return inspLocation;
	}
	public void setInspLocation(String inspLocation) {
		this.inspLocation = inspLocation;
	}
	
	public String getInspDriverCompany() {
		return inspDriverCompany;
	}
	public void setInspDriverCompany(String inspDriverCompany) {
		this.inspDriverCompany = inspDriverCompany;
	}
	public String getInspRemarks() {
		return inspRemarks;
	}
	public void setInspRemarks(String inspRemarks) {
		this.inspRemarks = inspRemarks;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}
	public BigDecimal getInspId() {
		return inspId;
	}
	public void setInspId(BigDecimal inspId) {
		this.inspId = inspId;
	}
	public BigDecimal getCustomerNr() {
		return customerNr;
	}
	public void setCustomerNr(BigDecimal customerNr) {
		this.customerNr = customerNr;
	}
	public int getUnitNr() {
		return unitNr;
	}
	public void setUnitNr(int unitNr) {
		this.unitNr = unitNr;
	}
	public BigDecimal getInspLocLongitude() {
		return inspLocLongitude;
	}
	public void setInspLocLongitude(BigDecimal inspLocLongitude) {
		this.inspLocLongitude = inspLocLongitude;
	}
	public BigDecimal getInspLocLatitude() {
		return inspLocLatitude;
	}
	public void setInspLocLatitude(BigDecimal inspLocLatitude) {
		this.inspLocLatitude = inspLocLatitude;
	}
	public BigDecimal getInspLocAltitude() {
		return inspLocAltitude;
	}
	public void setInspLocAltitude(BigDecimal inspLocAltitude) {
		this.inspLocAltitude = inspLocAltitude;
	}
	public String getDocstatus() {
		return docstatus;
	}
	public void setDocstatus(String docstatus) {
		this.docstatus = docstatus;
	}
	public int getDocuwarreDocId() {
		return docuwarreDocId;
	}
	public void setDocuwarreDocId(int docuwarreDocId) {
		this.docuwarreDocId = docuwarreDocId;
	}
	public void setTimeZoneOffset(String timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	
	public String getAppCd() {
		return appCd;
	}
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	
}
