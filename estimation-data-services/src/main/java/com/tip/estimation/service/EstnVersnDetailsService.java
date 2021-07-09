package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.VersionDetailsRequest;

@FunctionalInterface
public interface EstnVersnDetailsService {

 public	Map<String, Object> getVersnDetails(VersionDetailsRequest versionDetailsRequest);

}
