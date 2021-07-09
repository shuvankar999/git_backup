package com.tip.technicianjob.repository;

import java.util.Map;

@FunctionalInterface
public interface TechnicianJobDataRepository {

    public Map<String, Object> getTechJobDataList(String ssoId, String branchId);
}
