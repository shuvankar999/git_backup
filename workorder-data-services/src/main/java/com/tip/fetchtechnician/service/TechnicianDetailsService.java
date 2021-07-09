package com.tip.fetchtechnician.service;

import com.tip.fetchtechnician.model.TechnicianDetails;
import com.tip.fetchtechnician.model.TechnicianDetailsRequest;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface TechnicianDetailsService {

    public Map<String, List<TechnicianDetails>> getTechnicianDetails(TechnicianDetailsRequest technicianDetailsRequest);
}
