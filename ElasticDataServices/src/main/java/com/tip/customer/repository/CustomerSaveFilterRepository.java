package com.tip.customer.repository;

import com.tip.equipmentdetails.model.SaveFilterFormRequest;

@FunctionalInterface
public interface CustomerSaveFilterRepository {

	public boolean saveFilterDetils(SaveFilterFormRequest saveFilterFormRequest);

}
