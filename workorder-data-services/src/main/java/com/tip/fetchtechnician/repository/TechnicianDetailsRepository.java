package com.tip.fetchtechnician.repository;

import com.tip.fetchtechnician.model.TechnicianDetailsRequest;

import java.util.Map;

@FunctionalInterface
public interface TechnicianDetailsRepository {

    public Map<String, Object> getTechnicianDetails(TechnicianDetailsRequest technicianDetailsRequest);

}
