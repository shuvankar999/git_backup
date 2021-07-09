package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.EstnTyreAttributeRequest;

@FunctionalInterface
public interface FetchEstnTyreAtrbteService {

 public	Map<String, Object> getTyreAttribute(EstnTyreAttributeRequest estnTyreAttributeRequest);

}
