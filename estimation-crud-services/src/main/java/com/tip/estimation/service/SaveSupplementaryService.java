package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.SaveSupplModel;

@FunctionalInterface
public interface SaveSupplementaryService {

	public Map<String, Object> saveAllSuppl(SaveSupplModel saveSupplModel);

}
