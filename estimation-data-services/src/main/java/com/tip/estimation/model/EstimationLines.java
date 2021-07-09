package com.tip.estimation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EstimationLines {

    protected BigDecimal estimationId;
    protected Integer langId;
    protected List<EstnWorkOrderObject> estnWorkOrderList;
    protected List<EstnPartDetails> estnUnmatchedPartList;
    protected List<EstnBundles> estnBundleList;
    protected List<EstnNotesObject> estnNotesObjectList;

    public List<EstnNotesObject> getEstnNotesObjectList() {
		return estnNotesObjectList;
	}
	public void setEstnNotesObjectList(List<EstnNotesObject> estnNotesObjectList) {
		this.estnNotesObjectList = estnNotesObjectList;
	}
	public BigDecimal getEstimationId() {
        return estimationId;
    }
    public void setEstimationId(BigDecimal value) {
        this.estimationId = value;
    }

    public Integer getLangId() {
        return langId;
    }
    public void setLangId(Integer value) {
        this.langId = value;
    }

    public List<EstnWorkOrderObject> getEstnWorkOrderList() {
        if (estnWorkOrderList == null) {
            estnWorkOrderList = new ArrayList<EstnWorkOrderObject>();
        }
        return this.estnWorkOrderList;
    }
    
    public void setEstnWorkOrderList(List<EstnWorkOrderObject> estnWorkOrderList) {
		this.estnWorkOrderList = estnWorkOrderList;
	}

    public List<EstnPartDetails> getEstnUnmatchedPartList() {
        if (estnUnmatchedPartList == null) {
            estnUnmatchedPartList = new ArrayList<EstnPartDetails>();
        }
        return this.estnUnmatchedPartList;
    }
    public void setEstnUnmatchedPartList(List<EstnPartDetails> estnUnmatchedPartList) {
		this.estnUnmatchedPartList = estnUnmatchedPartList;
	}
	public List<EstnBundles> getEstnBundleList() {
        if (estnBundleList == null) {
            estnBundleList = new ArrayList<EstnBundles>();
        }
        return this.estnBundleList;
    }
	public void setEstnBundleList(List<EstnBundles> estnBundleList) {
		this.estnBundleList = estnBundleList;
	}
    
}
