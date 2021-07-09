package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.ValidatePartRequest;

@FunctionalInterface
public interface ValidateEstnPartService {

	public Map<String, Object> validatePart(ValidatePartRequest validatePartRequest);

}
