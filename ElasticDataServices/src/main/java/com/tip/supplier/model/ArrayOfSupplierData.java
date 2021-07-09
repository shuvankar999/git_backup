package com.tip.supplier.model;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfSupplierData {
	protected List<SupplierSearchResponse> supplierDataItem;
	protected Long count;

	public List<SupplierSearchResponse> getSupplierDataItem() {
		if (supplierDataItem == null) {
			supplierDataItem = new ArrayList<SupplierSearchResponse>();
		}
		return this.supplierDataItem;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
