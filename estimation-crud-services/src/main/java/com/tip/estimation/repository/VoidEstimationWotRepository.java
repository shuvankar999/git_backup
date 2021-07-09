package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.VoidEstnWotRequest;

@FunctionalInterface
public interface VoidEstimationWotRepository {

	public Map<String, Object> voidEstnWot(VoidEstnWotRequest voidEstnWotRequest);
}
