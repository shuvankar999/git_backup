package com.tip.technicianjob.service;

import java.util.Map;

@FunctionalInterface
public interface TechnicianJobDataService {

    public Map<String, Object> getTechJobDataList(String ssoId, String branchId);
}
