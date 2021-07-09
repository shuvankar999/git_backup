package com.tip.fetchworkorder.service;

import com.tip.fetchworkorder.model.WorkPackResponse;

import java.math.BigDecimal;

@FunctionalInterface
public interface FetchWorkorderService {

    public WorkPackResponse getWPackWorderTaskList(BigDecimal workPackNr, int languageId);

}
