package com.tip.fetchworkorder.repository;

import java.math.BigDecimal;
import java.util.Map;

@FunctionalInterface
public interface FetchWorkorderRepository {

    public Map<String, Object> getWPackWorderTaskList(BigDecimal workPackNr, int languageId);
}
