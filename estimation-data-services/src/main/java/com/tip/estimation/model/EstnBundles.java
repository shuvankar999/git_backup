package com.tip.estimation.model;

import java.util.ArrayList;
import java.util.List;

public class EstnBundles {

    protected String estnbundleName;
    protected List<EstnBundleDetails> listOfBundle;

    public String getEstnbundleName() {
        return estnbundleName;
    }

    public void setEstnbundleName(String value) {
        this.estnbundleName = value;
    }

    public List<EstnBundleDetails> getListOfBundle() {
        if (listOfBundle == null) {
            listOfBundle = new ArrayList<EstnBundleDetails>();
        }
        return this.listOfBundle;
    }

	public void setListOfBundle(List<EstnBundleDetails> listOfBundle) {
		this.listOfBundle = listOfBundle;
	}

}
