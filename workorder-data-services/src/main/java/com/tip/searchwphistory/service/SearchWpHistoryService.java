package com.tip.searchwphistory.service;

import java.util.Map;

import com.tip.searchwphistory.model.SearchRange;

@FunctionalInterface
public interface SearchWpHistoryService {

	public Map<String, Object> getWpHistory(SearchRange searchRange);
}
