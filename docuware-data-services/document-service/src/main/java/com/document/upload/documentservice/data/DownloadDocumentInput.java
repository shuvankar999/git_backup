package com.document.upload.documentservice.data;

public class DownloadDocumentInput {
	
	private String documentId;
	private String documentName;
	//private String cabinate;
	private String appCd;
	
	
	
	public String getAppCd() {
		return appCd;
	}
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	
	

}
