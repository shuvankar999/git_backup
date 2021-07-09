package com.tip.fetchwpvalidate.service;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.model.FetchWPValidateResponse;

@FunctionalInterface
public interface FetchWPValidateService {

    public FetchWPValidateResponse getWPValidateDetails(FetchWPValidateRequest fetchWPValidateRequest);

}
