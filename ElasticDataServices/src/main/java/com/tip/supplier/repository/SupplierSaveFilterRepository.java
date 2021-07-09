package com.tip.supplier.repository;

import com.tip.equipmentdetails.model.SaveFilterFormRequest;

@FunctionalInterface
public interface SupplierSaveFilterRepository {

	public boolean saveFilterDetils(SaveFilterFormRequest saveFilterFormRequest);

}
