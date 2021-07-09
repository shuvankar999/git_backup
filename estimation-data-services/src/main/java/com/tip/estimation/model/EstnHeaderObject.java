package com.tip.estimation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class EstnHeaderObject {

	protected BigDecimal estimationId;
    protected String estnTitle;
    protected String custNotes;
    protected String internalNotes;
    protected String currency;
    protected BigDecimal vat;
    protected BigDecimal vatValue;
    protected Boolean showHide;
    protected BigDecimal concession;
    protected Integer estnStatusId;
    protected String estnStatus;
    protected Integer version;
    protected Integer supplementary;
    protected BigDecimal wpNumber;
    protected Integer assetNumber;
    protected Integer companyNr;
    protected List<EstnWoListObject> estnWoList;
    
    
    

    public BigDecimal getEstimationId() {
		return estimationId;
	}

	public void setEstimationId(BigDecimal estimationId) {
		this.estimationId = estimationId;
	}

    public String getEstnTitle() {
        return estnTitle;
    }

    public void setEstnTitle(String string) {
        this.estnTitle = string;
    }
    public String getCustNotes() {
        return custNotes;
    }
    public void setCustNotes(String value) {
        this.custNotes = value;
    }
    public String getInternalNotes() {
        return internalNotes;
    }
    public void setInternalNotes(String value) {
        this.internalNotes = value;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String value) {
        this.currency = value;
    }
    public BigDecimal getVat() {
        return vat;
    }
    public void setVat(BigDecimal bigDecimal) {
        this.vat = bigDecimal;
    }
    public BigDecimal getVatValue() {
        return vatValue;
    }
    public void setVatValue(BigDecimal bigDecimal) {
        this.vatValue = bigDecimal;
    }
    public Boolean getShowHide() {
        return showHide;
    }
    public void setShowHide(Boolean flag) {
        this.showHide = flag;
    }
    public BigDecimal getConcession() {
        return concession;
    }
    public void setConcession(BigDecimal bigDecimal) {
        this.concession = bigDecimal;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer value) {
        this.version = value;
    }
    public Integer getSupplementary() {
        return supplementary;
    }
    public void setSupplementary(Integer value) {
        this.supplementary = value;
    }
    public List<EstnWoListObject> getEstnWoList() {
        if (estnWoList == null) {
            estnWoList = new ArrayList<EstnWoListObject>();
        }
        return this.estnWoList;
    }

	public Integer getEstnStatusId() {
		return estnStatusId;
	}

	public void setEstnStatusId(Integer estnStatusId) {
		this.estnStatusId = estnStatusId;
	}

	public String getEstnStatus() {
		return estnStatus;
	}

	public void setEstnStatus(String estnStatus) {
		this.estnStatus = estnStatus;
	}

	public void setEstnWoList(List<EstnWoListObject> estnWoList) {
		this.estnWoList = estnWoList;
	}

	public BigDecimal getWpNumber() {
		return wpNumber;
	}

	public void setWpNumber(BigDecimal wpNumber) {
		this.wpNumber = wpNumber;
	}

	public Integer getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(Integer assetNumber) {
		this.assetNumber = assetNumber;
	}

	public Integer getCompanyNr() {
		return companyNr;
	}

	public void setCompanyNr(Integer companyNr) {
		this.companyNr = companyNr;
	}

}
