package com.tip.fetchwohistory.repository;

import com.tip.fetchwohistory.model.WorkOrderRequest;

import java.util.Map;

@FunctionalInterface
public interface FetchWoHistoryRepository {

    public Map<String, Object> getWoHistory(WorkOrderRequest workOrderRequest);

}
