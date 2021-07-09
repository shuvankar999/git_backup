package com.tip.searchwphistory.repository;

import java.util.Map;

import com.tip.searchwphistory.model.SearchRange;

@FunctionalInterface
public interface SearchWpHistoryRepository {

	public Map<String, Object> getWpHistory(SearchRange searchRange);

}
