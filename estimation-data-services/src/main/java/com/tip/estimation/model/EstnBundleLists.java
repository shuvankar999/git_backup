
package com.tip.estimation.model;

import java.util.ArrayList;
import java.util.List;

public class EstnBundleLists {

	protected String bundleName;
	protected Boolean showHide;
	protected String isApproved;
    protected String isRejected;

	protected List<EstnBundleObject> listOfBundle;

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}
	
	

	public Boolean getShowHide() {
		return showHide;
	}

	public void setShowHide(Boolean showHide) {
		this.showHide = showHide;
	}

	public List<EstnBundleObject> getListOfBundle() {
		if (listOfBundle == null) {
			listOfBundle = new ArrayList<EstnBundleObject>();
		}
		return this.listOfBundle;
	}

	public void setListOfBundle(List<EstnBundleObject> listOfBundle) {
		this.listOfBundle = listOfBundle;
	}
	
    public String getIsApproved() {
        return isApproved;
    }

 
    public void setIsApproved(String value) {
        this.isApproved = value;
    }


    public String getIsRejected() {
        return isRejected;
    }

  
     
    public void setIsRejected(String value) {
        this.isRejected = value;
    }


	

}