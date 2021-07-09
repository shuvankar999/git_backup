package com.tip.fetchwpvalidate.repository;

import com.tip.fetchwpvalidate.model.FetchWPValidateRequest;
import com.tip.fetchwpvalidate.model.FetchWPValidateResponse;

@FunctionalInterface
public interface FetchWPValidateRepository {

    public FetchWPValidateResponse fetchWPValidateData(FetchWPValidateRequest fetchWPValidateRequest);
}
