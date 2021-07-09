package com.tip.searchwphistory.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.searchwphistory.model.SearchRange;
import com.tip.searchwphistory.repository.SearchWpHistoryRepository;
import com.tip.searchwphistory.service.SearchWpHistoryService;

@Service
@Transactional
public class SearchWpHistoryServiceImpl implements SearchWpHistoryService {

    @Autowired
    SearchWpHistoryRepository searchWpHistoryRepository;
    
    @Override
    public Map<String, Object> getWpHistory(SearchRange searchRange) {
        return searchWpHistoryRepository.getWpHistory(searchRange);
    }

}
