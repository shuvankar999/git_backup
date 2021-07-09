package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.CreateSupplRequest;

@FunctionalInterface
public interface CreateSupplService {

	public Map<String, Object> createSuppl(CreateSupplRequest createSupplRequest, String ssoId);

}
