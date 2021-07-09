package com.tip.fetchwphistory.repository;

import com.tip.fetchwphistory.model.WorkPackRequest;

import java.util.Map;

@FunctionalInterface
public interface FetchWpHistoryRepository {


    public Map<String, Object> getWpHistory(WorkPackRequest workPackRequest);
}
