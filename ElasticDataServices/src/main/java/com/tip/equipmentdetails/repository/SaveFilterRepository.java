package com.tip.equipmentdetails.repository;

import com.tip.equipmentdetails.model.SaveFilterFormRequest;

@FunctionalInterface
public interface SaveFilterRepository {

	public boolean saveFilterDetils(SaveFilterFormRequest saveFilterFormRequest);
}