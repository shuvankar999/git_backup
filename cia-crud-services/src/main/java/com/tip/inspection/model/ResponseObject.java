package com.tip.inspection.model;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class ResponseObject {

	private InputStream inputStream;
	private OutputStream outputStream ;
	private File file ;
	private Map<String,String> docuwareInput;
	private String docName;
	 
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public OutputStream getOutputStream() {
		return outputStream;
	}
	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	public Map<String, String> getDocuwareInput() {
		return docuwareInput;
	}
	public void setDocuwareInput(Map<String, String> docuwareInput) {
		this.docuwareInput = docuwareInput;
	}
}
