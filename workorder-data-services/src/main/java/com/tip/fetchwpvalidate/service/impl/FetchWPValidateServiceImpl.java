package com.tip.fetchwpvalidate.service.impl;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.model.FetchWPValidateResponse;
import com.tip.fetchwpvalidate.repository.FetchWPValidateRepository;
import com.tip.fetchwpvalidate.service.FetchWPValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FetchWPValidateServiceImpl implements FetchWPValidateService {

    @Autowired
    FetchWPValidateRepository fetchWPValidateRepository;

    @Override
    public FetchWPValidateResponse getWPValidateDetails(FetchWPValidateRequest fetchWPValidateRequest) {

        return fetchWPValidateRepository.fetchWPValidateData(fetchWPValidateRequest);
    }

}
