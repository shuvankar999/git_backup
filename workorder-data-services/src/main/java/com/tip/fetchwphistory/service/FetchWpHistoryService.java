package com.tip.fetchwphistory.service;


import com.tip.fetchwphistory.model.WorkPackRequest;

import java.util.Map;

@FunctionalInterface
public interface FetchWpHistoryService {

    public Map<String, Object> getWpHistory(WorkPackRequest workPackRequest);

}
