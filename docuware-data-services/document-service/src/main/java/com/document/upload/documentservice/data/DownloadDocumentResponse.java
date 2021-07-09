package com.document.upload.documentservice.data;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadDocumentResponse {

	private InputStream inputStream;
	private OutputStream outputStream ;
	private File file ;
	 
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
}
