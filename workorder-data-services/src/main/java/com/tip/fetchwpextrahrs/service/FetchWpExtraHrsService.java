package com.tip.fetchwpextrahrs.service;

import java.util.Map;

import com.tip.fetchwpextrahrs.model.ExtraHrsRequest;

@FunctionalInterface
public interface FetchWpExtraHrsService {

	public Map<String, Object> getWpExtraHrs(ExtraHrsRequest extraHrsRequest);

}
