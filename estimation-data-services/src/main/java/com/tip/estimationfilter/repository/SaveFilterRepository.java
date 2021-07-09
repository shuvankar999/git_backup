package com.tip.estimationfilter.repository;

import com.tip.estimationfilter.model.SaveFilterFormRequest;

@FunctionalInterface
public interface SaveFilterRepository {

	public boolean saveFilterDetils(SaveFilterFormRequest saveFilterFormRequest);

}
