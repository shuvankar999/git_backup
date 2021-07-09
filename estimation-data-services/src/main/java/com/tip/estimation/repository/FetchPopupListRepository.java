package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.FetchEstnPopupDetailsRequest;

@FunctionalInterface
public interface FetchPopupListRepository {
	
	public Map<String, Object> getPopupList(FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest);

}
