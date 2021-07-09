package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.DeletePartsRequest;

@FunctionalInterface
public interface DeletePartsEstnRepository {

	public Map<String, Object> deleteParts(DeletePartsRequest deletePartsRequest);
}
