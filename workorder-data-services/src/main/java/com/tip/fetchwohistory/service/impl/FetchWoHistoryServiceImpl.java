package com.tip.fetchwohistory.service.impl;

import com.tip.fetchwohistory.model.WorkOrderRequest;
import com.tip.fetchwohistory.repository.FetchWoHistoryRepository;
import com.tip.fetchwohistory.service.FetchWoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class FetchWoHistoryServiceImpl implements FetchWoHistoryService {

    @Autowired
    FetchWoHistoryRepository fetchWoHistoryRepository;

    @Override
    public Map<String, Object> getWoHistory(WorkOrderRequest workOrderRequest) {

        return fetchWoHistoryRepository.getWoHistory(workOrderRequest);
    }


}
