package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.EquipSearchRequest;

@FunctionalInterface
public interface EquipSearchService {

	public Map<String, Object> getEquipment(EquipSearchRequest equipSearchRequest);

}
