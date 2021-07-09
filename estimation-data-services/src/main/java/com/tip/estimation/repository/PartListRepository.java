package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.PartListRequest;
@FunctionalInterface
public interface PartListRepository {

	public Map<String, Object> getPartList(PartListRequest partListRequest);

}
