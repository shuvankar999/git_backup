package com.tip.fetchwpextrahrs.repository;

import java.util.Map;

import com.tip.fetchwpextrahrs.model.ExtraHrsRequest;

@FunctionalInterface
public interface FetchWpExtraHrsRepository {

	public Map<String, Object> getWpExtraHrs(ExtraHrsRequest extraHrsRequest);
}
