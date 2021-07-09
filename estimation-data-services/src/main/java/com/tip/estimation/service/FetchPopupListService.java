package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.FetchEstnPopupDetailsRequest;

@FunctionalInterface
public interface FetchPopupListService {

	public Map<String, Object> getPopupList(FetchEstnPopupDetailsRequest fetchEstnPopupDetailsRequest);

}
