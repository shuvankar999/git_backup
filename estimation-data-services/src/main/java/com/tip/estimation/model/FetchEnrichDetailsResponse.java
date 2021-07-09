package com.tip.estimation.model;

import java.util.ArrayList;
import java.util.List;

public class FetchEnrichDetailsResponse {

	protected EstnHeaderObject estnHeaderList;
	protected List<EstnPartLists> estnUnMatchedPartLists;
	protected List<EnrichTyreSpecsObject> tyreSpeclist;
	protected List<EnrichTyreServiceObject> tyreServicelist;
	protected List<EnrichConsumablesObject> estnConsumablesDetails;
	protected List<EstnBundleLists> estnBundleLists;
	protected List<EstnFeeList> estnFeeList;
	protected List<ManagementFees> mangementFeesList;
	protected List<DiscountMarkup> discountMarkupList;
					
		
	public EstnHeaderObject getEstnHeaderList() {
		return estnHeaderList;
	}

	public void setEstnHeaderList(EstnHeaderObject estnHeaderList) {
		this.estnHeaderList = estnHeaderList;
	}

	public List<EstnFeeList> getEstnFeeList() {
		return estnFeeList;
	}

	public void setEstnFeeList(List<EstnFeeList> estnFeeList) {
		this.estnFeeList = estnFeeList;
	}

	public List<EstnPartLists> getEstnUnMatchedPartLists() {
		  if (estnUnMatchedPartLists == null) {
			  estnUnMatchedPartLists = new ArrayList<>();
	        }
	        return this.estnUnMatchedPartLists;
	}

	public void setEstnUnMatchedPartLists(List<EstnPartLists> estnUnMatchedPartLists) {
		this.estnUnMatchedPartLists = estnUnMatchedPartLists;
	}

	public List<EstnBundleLists> getEstnBundleLists() {
		 if (estnBundleLists == null) {
			 estnBundleLists = new ArrayList<>();
	        }
	        return this.estnBundleLists;
	}

	public void setEstnBundleLists(List<EstnBundleLists> estnBundleLists) {
		this.estnBundleLists = estnBundleLists;
	}

	public List<EnrichTyreSpecsObject> getTyreSpeclist() {
		return tyreSpeclist;
	}

	public void setTyreSpeclist(List<EnrichTyreSpecsObject> tyreSpeclist) {
		this.tyreSpeclist = tyreSpeclist;
	}

	public List<EnrichTyreServiceObject> getTyreServicelist() {
		return tyreServicelist;
	}

	public void setTyreServicelist(List<EnrichTyreServiceObject> tyreServicelist) {
		this.tyreServicelist = tyreServicelist;
	}

	public List<EnrichConsumablesObject> getEstnConsumablesDetails() {
		return estnConsumablesDetails;
	}

	public void setEstnConsumablesDetails(List<EnrichConsumablesObject> estnConsumablesDetails) {
		this.estnConsumablesDetails = estnConsumablesDetails;
	}

	public List<ManagementFees> getMangementFeesList() {
		if (mangementFeesList == null) {
			mangementFeesList = new ArrayList<>();
	        }
		return mangementFeesList;
	}

	public void setMangementFeesList(List<ManagementFees> mangementFeesList) {
		this.mangementFeesList = mangementFeesList;
	}

	public List<DiscountMarkup> getDiscountMarkupList() {
		if (discountMarkupList == null) {
			discountMarkupList = new ArrayList<>();
	        }
		return discountMarkupList;
	}

	public void setDiscountMarkupList(List<DiscountMarkup> discountMarkupList) {
		this.discountMarkupList = discountMarkupList;
	}

	
	
}