package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.ValidatePartRequest;

@FunctionalInterface
public interface ValidateEstnPartRepository {
	public Map<String, Object> validatePart(ValidatePartRequest validatePartRequest); 

}
