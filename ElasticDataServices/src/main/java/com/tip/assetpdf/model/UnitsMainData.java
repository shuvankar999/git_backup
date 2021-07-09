package com.tip.assetpdf.model;

import java.awt.Image;

import org.springframework.web.multipart.MultipartFile;

public class UnitsMainData {
	
	private String unitNr;
	private String unitCatgrpCode;
	private String printFormat;
	private Image file;
	
	

	
	
	

	public Image getFile() {
		return file;
	}

	public void setFile(Image file) {
		this.file = file;
	}

	public String getPrintFormat() {
		return printFormat;
	}

	public void setPrintFormat(String printFormat) {
		this.printFormat = printFormat;
	}

	public String getUnitCatgrpCode() {
		return unitCatgrpCode;
	}

	public void setUnitCatgrpCode(String unitCatgrpCode) {
		this.unitCatgrpCode = unitCatgrpCode;
	}

	public String getUnitNr() {
		return unitNr;
	}

	public void setUnitNr(String unitNr) {
		this.unitNr = unitNr;
	}
	
	
	

}
