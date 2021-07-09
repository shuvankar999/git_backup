package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.EquipSearchRequest;

@FunctionalInterface
public interface EquipSearchRepository {

	Map<String, Object> getEquipment(EquipSearchRequest equipSearchRequest);

}
