package com.tip.fetchwohistory.service;

import com.tip.fetchwohistory.model.WorkOrderRequest;

import java.util.Map;

@FunctionalInterface
public interface FetchWoHistoryService {

    public Map<String, Object> getWoHistory(WorkOrderRequest workOrderRequest);

}
