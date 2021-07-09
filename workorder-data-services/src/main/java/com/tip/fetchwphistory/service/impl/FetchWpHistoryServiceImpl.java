package com.tip.fetchwphistory.service.impl;

import com.tip.fetchwphistory.model.WorkPackRequest;
import com.tip.fetchwphistory.repository.FetchWpHistoryRepository;
import com.tip.fetchwphistory.service.FetchWpHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class FetchWpHistoryServiceImpl implements FetchWpHistoryService {

    @Autowired
    FetchWpHistoryRepository fetchWpHistoryRepository;

    @Override
    public Map<String, Object> getWpHistory(WorkPackRequest workPackRequest) {
        return fetchWpHistoryRepository.getWpHistory(workPackRequest);
    }

}
